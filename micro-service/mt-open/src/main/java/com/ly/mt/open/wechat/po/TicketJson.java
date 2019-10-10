package com.ly.mt.open.wechat.po;

public class TicketJson {

    private int errcode;
    private String errmsg;
    private String ticket;
    private int expires_in;

    public int getErrcode()
    {
        return this.errcode;
    }

    public void setErrcode(int errcode)
    {
        this.errcode = errcode;
    }

    public String getErrmsg()
    {
        return this.errmsg;
    }

    public void setErrmsg(String errmsg)
    {
        this.errmsg = errmsg;
    }

    public String getTicket()
    {
        return this.ticket;
    }

    public void setTicket(String ticket)
    {
        this.ticket = ticket;
    }

    public int getExpires_in()
    {
        return this.expires_in;
    }

    public void setExpires_in(int expires_in)
    {
        this.expires_in = expires_in;
    }

}
