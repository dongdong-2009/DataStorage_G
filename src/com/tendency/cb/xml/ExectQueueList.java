package com.tendency.cb.xml;

import java.util.List;

import com.tendency.cb.util.GetListArrayItem;

public interface ExectQueueList
{


    public void ExectMessage(String data);

    public void Close();

    public void MutilMessage(List<String> data);
}
