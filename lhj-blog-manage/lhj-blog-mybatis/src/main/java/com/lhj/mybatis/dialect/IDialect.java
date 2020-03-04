package com.lhj.mybatis.dialect;

public abstract interface IDialect
{
  public abstract boolean supportsLimit();

  public abstract boolean supportsLimitOffset();

  public abstract String getLimitString(String paramString, int paramInt1, int paramInt2);
}

