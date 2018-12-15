using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
using System.Runtime.Serialization.Formatters.Binary;
using System.IO;

public class Authorization : MonoBehaviour {


    public InputField log;
    
    public InputField pass;
    public Text message;
    public Toggle flag;

    string token = "";


    //public void Start()
    //{
    //    log.contentType = InputField.ContentType.Alphanumeric;
    //    pass.contentType = InputField.ContentType.Password;
    //}

 

    //public void Toggle_change(bool value)
    //{
    //    if (value)
    //    {
    //        pass.contentType = InputField.ContentType.Standard;

    //    }
    //    else
    //    {
    //        pass.contentType = InputField.ContentType.Password;
    //    }
    //}

    //авторизация
    public void CliAuthorization()
        {
            StartCoroutine(Login());
        }

    public IEnumerator Login()
    {
        WWWForm form = new WWWForm();
        form.AddField("login", log.text);
        form.AddField("password", pass.text);

        WWW www = new WWW("http://localhost:8080/users/logIn", form);
        yield return www;
        if (www.error != null)
        {
            Debug.Log("Сервер ответил нет такого пользователя " + www.error);
            Debug.Log(www.error);
            message.text = "Неверный логин или пароль";
            yield break;
        }

        Debug.Log("Пользователь авторизован " + www.text);
        Repos r = new Repos();
        r.Save(www.text);
        Debug.Log("ПРОверка "+ r.R);
        token = r.R;
        Debug.Log("Прроверка 2 "+ token);
        //проверка, сохраняет ли
        SceneManager.LoadScene("welcome");
    }

    private void OnDisable()
    {
        PlayerPrefs.SetString("tokenUser", token);
        Debug.Log("Disable  "+ token);
    }

    //public static void Save(string str)
    //    {

    //        BinaryFormatter bf = new BinaryFormatter();
    //        FileStream file = File.Create(Application.persistentDataPath + "/savedToken.gd");
    //        bf.Serialize(file, str);
    //        Debug.Log("Файл успех");
    //        file.Close();
    //    }

    [System.Serializable]
    public class Repos
    {
        public string R { get; set; }
        public void Save(string str)
        {
            R = str;
        }

  
    }


}

