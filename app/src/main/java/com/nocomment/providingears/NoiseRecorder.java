package com.nocomment.providingears;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;

import com.getpebble.android.kit.PebbleKit;

import java.util.UUID;

/**
 * Created by Joey on 10/25/2015.
 */
public class NoiseRecorder {

    public static double REFERENCE = 0.00002;

    public double getNoiseLevel() {
        int bufferSize = AudioRecord.getMinBufferSize(11025, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
        //making the buffer bigger....
        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                11025, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        short data[] = new short[bufferSize];
        double average = 0.0;
        recorder.startRecording();
        //recording data;
        recorder.read(data, 0, bufferSize);

        recorder.stop();
        Log.e("TAG", "stop");
        for (short s : data) {
            if (s > 0) {
                average += Math.abs(s);
            } else {
                bufferSize--;
            }
        }
        //x=max;
        double x = average / bufferSize;
        recorder.release();
        double db = 0;
        // calculating the pascal pressure based on the idea that the max amplitude (between 0 and 32767) is
        // relative to the pressure
        double pressure = x / 51805.5336; //the value 51805.5336 can be derived from asuming that x=32767=0.6325 Pa and x=1 = 0.00002 Pa (the reference value)
        db = (20 * Math.log10(pressure / REFERENCE));
        return db;
    }

    public double highestNoiseLevel() {
        int bufferSize = AudioRecord.getMinBufferSize(11025, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT);
        //making the buffer bigger....
        AudioRecord recorder = new AudioRecord(MediaRecorder.AudioSource.MIC,
                11025, AudioFormat.CHANNEL_IN_DEFAULT, AudioFormat.ENCODING_PCM_16BIT, bufferSize);

        short data[] = new short[bufferSize];
        double highest = 0.0;
        recorder.startRecording();
        //recording data;
        recorder.read(data, 0, bufferSize);

        recorder.stop();
        Log.e("TAG", "stop");
        for (short s : data) {
            if (s > 0) {
                if (Math.abs(s) > highest) {
                    highest = Math.abs(s);
                }
            } else {
                bufferSize--;
            }
        }
        recorder.release();
        double db = 0;
        // calculating the pascal pressure based on the idea that the max amplitude (between 0 and 32767) is
        // relative to the pressure
        double pressure = highest / 51805.5336; //the value 51805.5336 can be derived from asuming that x=32767=0.6325 Pa and x=1 = 0.00002 Pa (the reference value)
        db = (20 * Math.log10(pressure / REFERENCE));
        return db;
    }
}
