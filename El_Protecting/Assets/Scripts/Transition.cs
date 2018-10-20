using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class Transition : MonoBehaviour {

    public Image img;
    public AnimationCurve curve;

    public void TransitionToProfile()
    {
        Application.LoadLevel("profile");
    }

    public void TransitionToWelcome()
    { 
        Application.LoadLevel("Welcom");
       
    }

    public void TransitionToQuestion()
    {
        Application.LoadLevel("question");
        
        
    }

    IEnumerator FadeIn()
    {
        float t = 0f; // сначала она 100% не прозрачна, поэтому t = 1

        while (t < 1f)
        {
            t -= Time.deltaTime * 1.5f; // отнимаем от t с каждым циклом время после посл. фрейма пока t не станет 0 или меньше. Тут я еще умнажаю на 1.5, тут уже смотрите как вам точнее нужно
            float a = curve.Evaluate(t); // теперь с помощью кривой оцениваем значение "a"
            img.color = new Color(0.28f, 0.5f, 0.78f, a); // и обновляем прозрачность картинки с помощью "a"
            yield return 0;
        }
    }

    IEnumerator FadeOut()
    {
        float t = 1f; // сначала она 100% не прозрачна, поэтому t = 1

        while (t > 0f)
        {
            t -= Time.deltaTime * 1.5f; // отнимаем от t с каждым циклом время после посл. фрейма пока t не станет 0 или меньше. Тут я еще умнажаю на 1.5, тут уже смотрите как вам точнее нужно
            float a = curve.Evaluate(t); // теперь с помощью кривой оцениваем значение "a"
            img.color = new Color(0.28f, 0.5f, 0.78f, a); // и обновляем прозрачность картинки с помощью "a"
            yield return 0;
        }
    }


}
