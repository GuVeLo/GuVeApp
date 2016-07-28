/*
package com.example.guvelo.navdrawertest;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

public class OpenGLActivity extends AppCompatActivity {

    private GLSurfaceView mGLView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_open_gl);
        mGLView = new ClearGLSurfaceView(this);
        setContentView(mGLView);

        @Override
        protected void onPause(){
            super.onPause();
            mGLView.onPause();
        }

        @Override
        protected void onResume() {
            super.onResume();
            mGLView.onResume();
        }

        class ClearGLSurfaceView extends GLSurfaceView {
            public ClearGLSurfaceView(Context context) {
                super(context);
                mRenderer = new ClearRenderer();
                setRenderer(mRenderer);
            }

            public boolean onTouchEvent(final MotionEvent event){
                queueEvent(new Runnable(){
                    public void run(){
                        mRenderer.setColor(event.getX() / getWidth(),
                                event.getY() / getHeight(), 1.0f);
                    }});
        }

        ClearRenderer mRenderer;
    }

    }
}

*/
