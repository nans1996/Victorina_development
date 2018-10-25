using UnityEngine;
using UnityEngine.UI;

public class NotifyController : MonoBehaviour {

    public Button[] bttns;
    private float currentTime;
    private int id = 1;

    private void Update()
    {
        if (currentTime > 0)
        {
            currentTime -= Time.deltaTime;
            foreach (Button b in bttns) b.interactable = false;
        }
        else if (currentTime <= 0) foreach (Button b in bttns) b.interactable = true;
    }

    public void turnOnNotif(int time)
    {
        AndroidNotification.SendNotification(1, time, "Отличный заголовок", "Тут может быть ваша реклама!", "Посмотри сюда!",Color.white,true,AndroidNotification.NotificationExecuteMode.Inexact,true,2,true,0,500);
        currentTime = time;
    }
    public void turnOnNotifNew(int time)
    {
        AndroidNotification.SendNotification(id, time, "Отличный заголовок", "Тут может быть ваша реклама!", "Посмотри сюда!", Color.white, true, AndroidNotification.NotificationExecuteMode.Inexact, true, 2, true, 0, 500);
        id++;
        currentTime = time;
    }
}