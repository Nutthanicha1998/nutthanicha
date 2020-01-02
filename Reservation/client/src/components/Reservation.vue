<template>
    <v-container>
   <v-card class="mx-auto" max-width="900" color ="#FFD54F">
     <v-spacer></v-spacer>
      <v-row justify="center">
        <v-toolbar-title>
          <h1>ระบบจองเข้าใช้สถานกีฬา</h1>
        </v-toolbar-title>
      </v-row>
      <v-spacer></v-spacer>
       <v-row justify="center">
       <v-col cols="15" sm="3" md="5">
          <v-text-field
            label="ID ผู้ใช้งาน"
            v-model="reservation.member"
            outlined
          ></v-text-field>
          <p v-if="memberCheck != ''">Member Name : {{memberName}} </p>
        </v-col>
         <div class="my-5">
          <v-btn @click="findMember" color="primary">Search</v-btn>
         </div>
       </v-row>
        <v-row justify="center">
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
          :items="fieldtypes"
          v-model="reservation.fieldtype"
          item-text="fieldtype_name"
          item-value="id"
          label="ประเภทสนามกีฬา"
          filled
          dense
          ></v-select>
        </v-col>
      </v-row>
      
      <v-row justify="center">
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
          v-model="reservation.fielduse"
          :items="fielduses"
          item-text="fielduse_name"
          item-value="id"
            filled
            label="ประเภทการใช้งานสนามกีฬา"
            dense
          ></v-select>
        </v-col>
      </v-row>
      <v-row justify="center">
        <v-col class="d-flex" cols="12" sm="6">
          <v-select
            v-model="reservation.employee"
          :items="employees"
          item-text="name"
          item-value="id"
            filled
            label="พนักงานที่จองสถานกีฬา"
            dense
          ></v-select>
        </v-col>
      </v-row>
      <v-row justify="center">
      <v-col cols="20" sm="3" md="6">
      <v-menu
        ref="menu"
        v-model="menu"
        :close-on-content-click="false"
        :return-value.sync="date"
        transition="scale-transition"
        offset-y
        min-width="290px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="date"
            label="ระบุวันจองสนามกีฬา"
            prepend-icon="mdi-calendar"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-date-picker v-model="date" no-title scrollable>
          <div class="flex-grow-1"></div>
          <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
          <v-btn text color="primary" @click="$refs.menu.save(date)">OK</v-btn>
        </v-date-picker>
      </v-menu>
    </v-col>
    </v-row>
    <v-row justify="center">
    
    <v-col cols="11" sm="5">
      <v-menu
        ref="menu1"
        v-model="menu1"
        :close-on-content-click="false"
        :nudge-right="20"
        :return-value.sync="time"
        transition="scale-transition"
        offset-y
        max-width="250px"
        min-width="250px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="time"
            label="เวลาที่เข้าจากสนามกีฬา"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu1"
          v-model="time"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>
     
      <v-col cols="11" sm="5">
      <v-menu
        ref="menu2"
        v-model="menu2"
        :close-on-content-click="false"
        :nudge-right="20"
        :return-value.sync="time"
        transition="scale-transition"
        offset-y
        max-width="250px"
        min-width="250px"
      >
        <template v-slot:activator="{ on }">
          <v-text-field
            v-model="time"
            label="เวลาที่ออกจากสนามกีฬา"
            prepend-icon="access_time"
            readonly
            v-on="on"
          ></v-text-field>
        </template>
        <v-time-picker
          v-if="menu2"
          v-model="time"
          full-width
          @click:minute="$refs.menu.save(time)"
        ></v-time-picker>
      </v-menu>
    </v-col>
         
    </v-row>
      <v-row justify="center"> <v-spacer></v-spacer>
       <div class="my-5">
          <v-btn @click="ShowReturn_id" color="#2E7D32">บันทึก</v-btn>
         </div>
     <v-spacer></v-spacer>
      
       <div class="my-5">
          <v-btn @click="ShowReturn_id" color="#FF5252">บันทึก</v-btn>
         </div> <v-spacer></v-spacer>
      </v-row>

   </v-card>
  </v-container>
</template>


<script>
 
import http from "../http-common";
export default {
  name: "reservation",
  data() {
    return {
      reservation: {
      id: undefined,
      fieldtype: undefined,
      fielduse: undefined,
      employee: undefined,
      date: undefined,
      starttime: undefined,
      endtime: undefined,
      time: null,
        menu: false,
        menu1: false,
        menu2: false,
        modal2: false
      },
      reservations:undefined,
      members: [],
      employees: [],
      fileduses: [],
      fieldtypes: [],
      memberCheck: false,
      memberName: ""
    };
  },
  methods: {

    getEmployees() {
      http
        .get("/employee")
        .then(response => {
          this.employees = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getMembers() {
      http
        .get("/member")
        .then(response => {
          this.members = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },

    getFieldtypes() {
      http
        .get("/fieldtype")
        .then(response => {
          this.fieldtypes = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    
    getFielduses() {
      http
        .get("/fielduse")
        .then(response => {
          this.fielduses = response.data;
          console.log(response.data);
        })
        .catch(e => {
          console.log(e);
        });
    },
    findMember(){
      http
      .get("/member/" + this.reservation.member)
      .then(response => {
        console.log(response);
        if(response.data != null){
          this.memberName = response.data.name;
          this.memberCheck = response.status;
        }else{
          this.clear()
        }
      })
      .catch(e => {
        console.log(e);
      });
      this.submitted = true;
    },
    
    saveReservation() {
       if (
        !this.reservation.id ||
        !this.reservation.fieldtype ||
        !this.reservation.fielduse ||
        !this.reservation.date ||
        !this.reservation.employee 
      ) {
        alert("ใส่ข้อมูลไม่ครบถ้วน");
      } else {
      http
        .post(
          "/reservation/" +
            this.reservation.id +
            "/" +
            this.reservation.fieldtype +
            "/" +
            this.reservation.fielduse +
            "/" +
            this.reservation.date +
            "/" +
            this.reservation.employee,
          this.reservation
         )
        .then(response => {
          console.log(response);
            this.$router.push("/");
        })
        .catch(e => {
          console.log(e);
        });
      this.submitted = true;

      alert("บันทึกเรียบร้อย");
      }
  },
    //clear() {
      //this.$refs.form.reset();
       // this.addjob.name = "";
       // this.addjob.user = "";
       // this.addjob.phone = "";
        //this.addjob.education = "";
        //this.addjob.jobPost = "";
        //this.addjob.information = "";
        //this.addjob.introduction = "";
    //},
    back(){
      this.$router.push("/");
    },
    refreshList() {
      this.getEmployees();
      this.getMembers();
      this.getFieldtypes();
      this.getFielduses();
    }   
  },
  mounted() {
    this.getEmployees();
      this.getMembers();
      this.getFieldtypes();
      this.getFielduses();
  }
};
</script>