package com.example.wholeprojectfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class workattach : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workattach)

        var layout = findViewById<RecyclerView>(R.id.workrv)

        var workary=ArrayList<workshop>()

        workary.add(workshop("2022 International Workshop on Integrated Nonlinear Microwave and Millimetre-Wave Circuits (INMMiC)","7 - 8 April 2022  |  Cardiff, United Kingdom  |  Event Format: In-person","Cardiff University, School of Engineering ; European Microwave Association - EuMA; Gallium Arsenide Application Symposium Association – GAAS; IEEE Microwave Theory and Techniques Society","Communication, Networking and Broadcast Technologies; Components, Circuits, Devices and Systems; Fields, Waves and Electromagnetics"))
        workary.add(workshop("2022 IEEE International Conference on Software Testing, Verification and Validation Workshops (ICSTW)","4 - 13 April 2022  |  Event Format: Virtual","IEEE Computer Society; Universidad Politecnica De Valencia - Spain","General Topics for Engineers"))
        workary.add(workshop("2022 10th Workshop on Satellite Navigation Technology (NAVITEC)","4 - 8 April 2022  |  Event Format: Virtual","Bundeswehr University Munich; CNES - French National Centre for Space Studies ; European Space Agency - European Space Research and Technology Centre; German Aerospace Center (DLR); IEEE Aerospace and Electronic Systems Society","Aerospace; Signal Processing and Analysis"))
        workary.add(workshop("2022 10th Workshop on Modelling and Simulation of Cyber-Physical Energy Systems (MSCPES)","3 May 2022  |  Event Format: Virtual","AIT - Austrian Institute of Technology GmbH; IEEE Industrial Electronics Society","Communication, Networking and Broadcast Technologies; Computing and Processing; Power, Energy and Industry Applications"))
        workary.add(workshop("2022 2nd International Workshop on Computation-Aware Algorithmic Design for Cyber-Physical Systems (CAADCPS)","3 - 6 May 2022  |  Event Format: Virtual","IEEE Computer Society"," Communication, Networking and Broadcast Technologies; Computing and Processing; Robotics and Control Systems"))
        workary.add(workshop("2022 International Workshop on Biometrics and Forensics (IWBF)","20 - 21 April 2022  |  Salzburg, Austria  |  Event Format: In-person"," Austria Section; University of Salzburg"," Bioengineering; Signal Processing and Analysis"))
        workary.add(workshop("IEEE INFOCOM 2022 - IEEE Conference on Computer Communications Workshops (INFOCOM WKSHPS)","2 - 5 May 2022  |  Event Format: Virtual"," IEEE Communications Society"," Communication, Networking and Broadcast Technologies"))
        workary.add(workshop("2022 Workshop on Benchmarking Cyber-Physical Systems and Internet of Things (CPS-IoTBench)","3 - 6 May 2022  |  Event Format: Virtual"," IEEE Computer Society"," Communication, Networking and Broadcast Technologies; Computing and Processing; Robotics and Control Systems"))
        workary.add(workshop("2022 Workshop on Cyber Physical Systems for Emergency Response (CPS-ER)","3 - 6 May 2022  |  Event Format: Virtual"," IEEE Computer Society","Communication, Networking and Broadcast Technologies; Computing and Processing; Robotics and Control Systems"))
        workary.add(workshop("2022 2nd International Workshop on Cyber-Physical-Human System Design and Implementation (CPHS)","3 - 6 May 2022  |  Event Format: Virtual"," IEEE Computer Society"," Communication, Networking and Broadcast Technologies; Computing and Processing; Robotics and Control Systems"))
        workary.add(workshop("2022 IEEE Workshop on Design Automation for CPS and IoT (DESTION)","3 - 6 May 2022  |  Event Format: Virtual","IEEE Computer Society","Communication, Networking and Broadcast Technologies; Computing and Processing; Robotics and Control Systems",))
        layout.layoutManager=GridLayoutManager(this,1)
        var adapter = workshopAdapter(this,workary)
        layout.adapter=adapter
    }
}