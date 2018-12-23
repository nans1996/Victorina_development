using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Timer : MonoBehaviour {

    public Text scoreText;

    int i = 1800;
    float sec = 0f;
    void Start()
    {
        InvokeRepeating("RunTimer", 1, 1);
    }

    void RunTimer()
    {
        float minut = i / 60;
         sec = i % 60;
        if ((sec == 0f)|| (sec == 1f) || (sec == 2f) || (sec == 3f) || (sec == 4f) || (sec == 5f)|| (sec == 6f)|| (sec == 7)|| (sec == 8) || (sec == 9))
        {
            scoreText.text = minut.ToString() + ":0" + sec.ToString();
            i--;
        }
        else
        {
            scoreText.text = minut.ToString() + ":"+ sec.ToString();
            i--;
        }
       
    }
}
