package com.tendency.cb.xml;

public class extecter
{
    public void executeMessage(ExectQueueList callBack, String question)
    {
        callBack.ExectMessage(question);
    }

    public void Close(ExectQueueList callBack)
    {
        callBack.Close();
    }
}
