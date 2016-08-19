package com.feicuiedu.videoplayer.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Surface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.vov.vitamio.MediaPlayer;

/**
 * 用来管理列表上的视频播放，列表中所有视频将共用一个MediaPlayer
 *
 * 此类将提供三对方法给UI层调用：
 * <ol>
 * <li/>onResume和onPause : 初始化和释放 (生命周期的保证)
 * <li/>startPlayer和stopPlayer : 开始播放和停止播放 (提供方法给视图来触发业务)
 * <li/>addPlayerbackListener和removeAllListeners :添加和移除监听(视图交互接口)
 * </ol>
 * 作者：yuanchao on 2016/8/19 0019 14:10
 * 邮箱：yuanchao@feicuiedu.com
 */
public class MediaPlayerManager {

    private static MediaPlayerManager sInstance;

    public synchronized static MediaPlayerManager getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new MediaPlayerManager(context.getApplicationContext());
        }
        return sInstance;
    }

    private final Context context; // 使用Application Context，避免出现内存泄漏

    private MediaPlayerManager(Context context){
        this.context = context;
    }

    private MediaPlayer mediaPlayer;

    public void onResume(){
        mediaPlayer = new MediaPlayer(context);
        // 进行一系列监听处理
        setMediaPlayerListener(mediaPlayer);
    }

    public void onPause(){
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private long startTime;
    public void startPlayer(
            @NonNull Surface surface,
            @NonNull String path,
            @NonNull String videoId){
        // 避免频繁快速开和关造成错误
        if(System.currentTimeMillis() - startTime < 300)return;
        startTime = System.currentTimeMillis();
        // 当前有视频在,先关闭视频
        if(this.videoId != null){
            stopPlayer();
        }
        // 更新为当前视频
        this.videoId = videoId;
        // 通知UI
        for(OnPlaybackListener listener:listeners){
            listener.onStartPlay(videoId);
        }
        try {
            mediaPlayer.setDataSource(path);
            mediaPlayer.setSurface(surface);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopPlayer(){
        if(videoId == null)return;
        // 通知UI
        for(OnPlaybackListener listener:listeners){
            listener.onStopPlay(videoId);
        }
        this.videoId = null;
        if(mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        mediaPlayer.reset();
    }

    private void setMediaPlayerListener(final  MediaPlayer mediaPlayer){
        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override public boolean onInfo(MediaPlayer mp, int what, int extra) {
                switch (what){
                    case MediaPlayer.MEDIA_INFO_FILE_OPEN_OK:
                        // Vitamio要做音频初始处理
                        long bufferSize = mediaPlayer.audioTrackInit();
                        mediaPlayer.audioInitedOk(bufferSize);
                        return true;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        if(mediaPlayer.isPlaying())mediaPlayer.pause();
                        // 开始buffer，通知UI
                        for(OnPlaybackListener listener:listeners){
                            listener.onStartBuffering(videoId);
                        }
                        return true;
                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        mediaPlayer.start();
                        // 结束buffer，通知UI
                        for(OnPlaybackListener listener:listeners){
                            listener.onStopBuffering(videoId);
                        }
                        return true;
                }
                return false;
            }
        });
        // 视频准备完成后,设置缓冲区大小并开始播放
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override public void onPrepared(MediaPlayer mp) {
                mediaPlayer.setBufferSize(512 * 1024);
                mediaPlayer.start();
            }
        });
        // 视频播放到最后,能知UI更新
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override public void onCompletion(MediaPlayer mp) {
                stopPlayer();
            }
        });
        // 获取到视频的大小后,通知UI更新
        mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
            @Override public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                if(width == 0 || height == 0)return;
                for(OnPlaybackListener listener:listeners){
                    listener.onSizeMeasured(videoId,width,height);
                }
            }
        });
    }

    private String videoId;// 视频ID，用来区分，是UI层的哪个视频
    private List<OnPlaybackListener> listeners = new ArrayList<>();

    public String getVideoId() {
        return videoId;
    }

    public void addPlaybackListener(OnPlaybackListener listener) {
        listeners.add(listener);
    }

    public void removeAllListener(){
        listeners.clear();
    }

    /** 列表视图接口*/
    public interface OnPlaybackListener{

        void onStartBuffering(String videoId);

        void onStopBuffering(String videoId);
        // 开始播放
        void onStartPlay(String videoId);
        // 停止播放
        void onStopPlay(String videoId);
        // 大小更改
        void onSizeMeasured(String videoId, int width,int height);
    }
}