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
        private Vector<Integer>group_id;
        private int rate_id;
        private Boolean has_paid;
        private Boolean is_late;
        private int due_amount;
        private String phone;





    public MessMember(String name, String start_date,String startof_month, Boolean is_active,Vector<Integer> group_id, int rate_id, Boolean has_paid,Boolean is_late, int due_amount, String phone )
        {
                this.name = name;
                this.start_date = start_date;
                this.startof_month = startof_month;
                this.is_active = is_active;
                this.group_id = group_id;
                this.rate_id = rate_id;
                this.has_paid = has_paid;
                this.is_late = is_late;
                this.due_amount = due_amount;
                this.phone = phone;
        }


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
         * @return.
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
            // TODO implement h
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


        public void setGroup_id(Vector<Integer> group_id)

        {
            this.group_id = group_id;
        }

        public void setStart_date(String start_date)
        {
            this.start_date = start_date;
        }

        public void setStartof_month(String startof_month)
        {
            this.startof_month = startof_month;
        }

        public void setIs_active(Boolean is_active)
        {
            this.is_active = is_active;
        }

        public void setHas_paid(Boolean has_paid)
        {
            this.has_paid = has_paid;
        }

        public void setRate_id(int rate_id)
        {
            this.rate_id = rate_id;
        }

        public void setIs_late(Boolean is_late)
        {
            this.is_late = is_late;
        }

        public void setDue_amount(int due_amount)
        {
            this.due_amount = due_amount;
        }
    }



