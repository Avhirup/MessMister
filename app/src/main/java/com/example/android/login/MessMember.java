package com.example.android.login;

import java.util.Vector;

/**
 * Created by medha on 27/10/15.
 */
public class MessMember {




        /**
         * Default constructor
         */
        public MessMember() {
        }


        private  int member_id;
        private String name;
        private String start_date;
        private String startof_month;
        private Boolean is_active;
        private Vector<Integer> group_id;
        private int rate_id;
        private Boolean has_paid;
        private Boolean is_late;
        private float due_amount;
        private String phone;



        /**
         * @param name
         * @return
         */
        public void setName(String name) {
            // TODO implement here

        }

        /**
         * @return
         */
        public String getName() {
            // TODO implement here
            return "";
        }

        /**
         * @return
         */
        public String getStartDate() {
            // TODO implement here
            return "";
        }

        /**
         * @return
         */
        public void setStartDate(String s) {
            // TODO implement here

        }

        /**
         * @return
         */
        public Boolean getIsActive() {
            // TODO implement here
            return null;
        }

        /**
         * @return
         */
        public void setIsActive(Boolean b) {
            // TODO implement here

        }



        /**
         * @return
         */
        public void setGroup(Vector<Integer> groups) {
            // TODO implement
        }

        public Vector<Integer> groupsgetGroup() {
        // TODO implement h
            return  null;
        }

        /**
         * @return
         */
        public int getRate() {
            // TODO implement here
            return 0;
        }

        public void setRateId(int id)
        {

        }

        /**
         * @return
         */
        public float getDueAmount() {
            // TODO implement here
            return 0;

        }

        public void setDueAmount(float amt)
        {

        }

        /**
         * @return
         */
        public Boolean checkIfLate() {
            // TODO implement here
            return null;
        }

        public int getMemberId()
        {
            return member_id;
        }

        public String getPhone()
        {
            return phone;
        }

        public void setPhone(String phone)
        {

        }
    }

