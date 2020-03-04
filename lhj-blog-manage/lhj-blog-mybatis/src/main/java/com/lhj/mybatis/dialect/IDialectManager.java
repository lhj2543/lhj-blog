package com.lhj.mybatis.dialect;

public abstract interface IDialectManager
{
  public abstract IDialect getDialect(String paramString);
}

