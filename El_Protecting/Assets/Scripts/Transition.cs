﻿using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Transition : MonoBehaviour {

	public void TransitionToProfile()
    {
        Application.LoadLevel("profile");
    }
}
