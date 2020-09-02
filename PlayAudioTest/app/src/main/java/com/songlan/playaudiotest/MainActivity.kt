package com.songlan.playaudiotest

import android.media.MediaPlayer
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMediaPlayer()

        playBtn.setOnClickListener(this)
        pauseBtn.setOnClickListener(this)
        stopBtn.setOnClickListener(this)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)
        playVideoBtn.setOnClickListener(this)
        pauseVideoBtn.setOnClickListener(this)
        stopVideoBtn.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.playBtn -> {
                if (!mediaPlayer.isPlaying) {
                    mediaPlayer.start()
                }
            }
            R.id.pauseBtn -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.pause()
                }
            }
            R.id.stopBtn -> {
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.reset()
                    initMediaPlayer()
                }
            }
            R.id.playVideoBtn -> {
                if(!videoView.isPlaying){
                    videoView.start()
                }
            }
            R.id.pauseVideoBtn -> {
                if(videoView.isPlaying){
                    videoView.pause()
                }
            }
            R.id.stopVideoBtn -> {
                videoView.resume()
            }
        }
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("石玺彤 - 就算你活在二次元.mp3")
        mediaPlayer.setDataSource(fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
        videoView.suspend()
    }
}