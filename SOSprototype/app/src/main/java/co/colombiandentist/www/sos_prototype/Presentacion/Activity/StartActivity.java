package co.colombiandentist.www.sos_prototype.Presentacion.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.MenuItem;
import android.view.View;

import com.github.rubensousa.bottomsheetbuilder.BottomSheetBuilder;
import com.github.rubensousa.bottomsheetbuilder.BottomSheetMenuDialog;
import com.github.rubensousa.bottomsheetbuilder.adapter.BottomSheetItemClickListener;
import com.github.rubensousa.bottomsheetbuilder.util.BottomSheetBuilderUtils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.ButterKnife;
import co.colombiandentist.www.sos_prototype.Logica.Message;
import co.colombiandentist.www.sos_prototype.Logica.Validacion;
import co.colombiandentist.www.sos_prototype.R;

public class StartActivity extends AppCompatActivity implements BottomSheetItemClickListener {

    public static final String STATE_HEADER = "state_header";

    private BottomSheetMenuDialog mBottomSheetDialog;
    private BottomSheetBehavior mBehavior;

    AppBarLayout appBarLayout;
    CoordinatorLayout coordinatorLayout;
    private boolean mShowingHeaderDialog;
    Context ctx;

    FirebaseAuth mAuth;

    TextInputEditText txUsuario;
    TextInputEditText txContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        ButterKnife.bind(this);
        setContentView(R.layout.activity_start);
        if(!Validacion.isLandscape(this)) {
            appBarLayout=(AppBarLayout)findViewById(R.id.app_bar);
        }

        coordinatorLayout = (CoordinatorLayout)findViewById(R.id.coordinatorLayout);
        try {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                getWindow().setStatusBarColor(Color.TRANSPARENT);
                getWindow().setEnterTransition(new Fade());
            }
        }catch (Exception ex){}
        View bottomSheet = new BottomSheetBuilder(this, coordinatorLayout)
                .setMode(BottomSheetBuilder.MODE_LIST)
                .setBackgroundColorResource(android.R.color.white)
                .setMenu(R.menu.options_start)
                .setItemClickListener(this)
                .createView();
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                if (newState == BottomSheetBehavior.STATE_COLLAPSED) {
                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
        mAuth = FirebaseAuth.getInstance();
        mAuth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser() != null){
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        Intent intent = new Intent(ctx, InicioActivity.class);
                        startActivity(intent);
                        return;
                    }
                    Message.mostrarMensaje(ctx, "El usuario no ha sido confirmado");
                }
            }
        });

        txUsuario = (TextInputEditText)findViewById(R.id.txUsuario);
        txContraseña = (TextInputEditText)findViewById(R.id.txContraseña);
    }


    public void ShowMoreOptions(View view){
        onShowDialogHeadersClick();
    }
    public void IniciarSesion(View view){
        mAuth.signInWithEmailAndPassword(txUsuario.getText().toString().trim(), txContraseña.getText().toString().trim()).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                e.printStackTrace();
                Message.mostrarMensaje(ctx, "Usuario o contraseña incorrectos");
            }
        });
    }


    @SuppressWarnings("unused")
    public void onShowDialogHeadersClick() {
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
        mShowingHeaderDialog = true;
        if(!Validacion.isLandscape(ctx)) {
            mBottomSheetDialog = new BottomSheetBuilder(this, R.style.AppTheme_BottomSheetDialog_Custom)
                    .setMode(BottomSheetBuilder.MODE_LIST)
                    .setAppBarLayout(appBarLayout)
                    .setMenu(R.menu.options_start)
                    .expandOnStart(true)
                    .setItemClickListener(new BottomSheetItemClickListener() {
                        @Override
                        public void onBottomSheetItemClick(MenuItem item) {
                            if(item.getItemId() == R.id.signUpItem){
                                Intent intent = new Intent(ctx, SignUpActivity.class);
                                startActivity(intent);
                            }
                            mShowingHeaderDialog = false;
                        }
                    })
                    .createDialog();
        }
        else{
            mBottomSheetDialog = new BottomSheetBuilder(this, R.style.AppTheme_BottomSheetDialog_Custom)
                    .setMode(BottomSheetBuilder.MODE_LIST)
                    .setMenu(R.menu.options_start)
                    .expandOnStart(true)
                    .setItemClickListener(new BottomSheetItemClickListener() {
                        @Override
                        public void onBottomSheetItemClick(MenuItem item) {
                            String s = getString(R.string.crear_una_cuenta);
                            if(s.equals(item.getTitle())){
                                Intent intent = new Intent(ctx, SignUpActivity.class);
                                startActivity(intent);
                            }
                            mShowingHeaderDialog = false;
                        }
                    })
                    .createDialog();
        }
        mBottomSheetDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mShowingHeaderDialog = false;
            }
        });
        mBottomSheetDialog.show();
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        BottomSheetBuilderUtils.saveState(outState, mBehavior);
        outState.putBoolean(STATE_HEADER, mShowingHeaderDialog);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        BottomSheetBuilderUtils.restoreState(savedInstanceState, mBehavior);

        if (savedInstanceState.getBoolean(STATE_HEADER)) onShowDialogHeadersClick();
    }

    @Override
    protected void onDestroy() {
        // Avoid leaked windows
        if (mBottomSheetDialog != null) {
            mBottomSheetDialog.dismiss();
        }
        super.onDestroy();
    }
    @Override
    public void onBottomSheetItemClick(MenuItem item) {
        mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
}
