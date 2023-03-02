package com.codurance.training.tasks.service;

public interface CheckService {
   void check(String idString);
   void uncheck(String idString);
   void setDone(String idString, boolean done);
}
