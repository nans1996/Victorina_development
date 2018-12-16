using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Timer : MonoBehaviour {

    public Text scoreText;

    int i = 1800;
    int sec = 0;
    void Start()
    {
        InvokeRepeating("RunTimer", 1, 1);
    }

    void RunTimer()
    {
        int minut = i / 60;
         sec = i % 60;
        if ((sec != 0)|| (sec != 1) || (sec != 2) || (sec != 3) || (sec != 4) || (sec != 5)|| (sec != 6)|| (sec != 7)|| (sec != 8)|| (sec != 9))
        {
            scoreText.text = minut.ToString() + ":" + sec.ToString();
            i--;
        }
        else
        {
            scoreText.text = minut.ToString() + ":0"+ sec.ToString();
            i--;
        }
       
    }
}
