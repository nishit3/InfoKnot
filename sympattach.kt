package com.example.wholeprojectfiles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class sympattach : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sympattach)

        var layout = findViewById<RecyclerView>(R.id.symprv)

        var symal = ArrayList<symposiam>()
        symal.add(symposiam("2022 International Symposium on Physical Design (ISPD)","27 - 30 March 2022  |  Event Format: Virtual","Association for Computing Machinery Special Interest Group on Design Automation - ACM SIGDA; IEEE Council on Electronic Design Automation","Components, Circuits, Devices and Systems"))
        symal.add(symposiam("2022 IEEE International Reliability Physics Symposium (IRPS)","27 - 31 March 2022  |  Dallas, Texas, USA  |  Event Format: In-person","IEEE Electron Devices Society; IEEE Reliability Society","Components, Circuits, Devices and Systems; Engineered Materials, Dielectrics and Plasmas; Engineering Profession"))
        symal.add(symposiam("2022 IEEE 19th International Symposium on Biomedical Imaging (ISBI)","28 - 31 March 2022  |  Kolkata, India  |  Event Format: In-person","IEEE Engineering in Medicine and Biology Society; IEEE Signal Processing Society"," Bioengineering; Computing and Processing; Photonics and Electrooptics; Signal Processing and Analysis"))
        symal.add(symposiam("2022 IEEE International Symposium on Inertial Sensors and Systems (INERTIAL)","28 - 31 March 2022  |  Avignon, France  |  Event Format: In-person","IEEE Sensors Council","General Topics for Engineers"))
        symal.add(symposiam("2022 IEEE/ACM International Symposium on Code Generation and Optimization (CGO)","2 - 6 April 2022  |  Event Format: Virtual","Association for Computing Machinery - ACM; IEEE Computer Society","Computing and Processing"))
        symal.add(symposiam("2022 IEEE International Symposium on High-Performance Computer Architecture (HPCA)","2 - 6 April 2022  |  Event Format: Virtual","IEEE Computer Society","Components, Circuits, Devices and Systems; Computing and Processing"))
        symal.add(symposiam("2022 Wireless Telecommunications Symposium (WTS)","6 - 8 April 2022  |  Pomona, California, USA  |  Event Format: Hybrid (In-person and Virtual)","Cal Poly Pomona Foundation, Inc.; IEEE Communications Society","Communication, Networking and Broadcast Technologies"))
        symal.add(symposiam("2022 25th International Symposium on Design and Diagnostics of Electronic Circuits and Systems (DDECS)","6 - 8 April 2022  |  Prague, Czech Republic  |  Event Format: Hybrid (In-person and Virtual)","Czech Technical Univ in Prague; Czechoslovakia Section C Chapter; IEEE Council on Electronic Design Automation","Components, Circuits, Devices and Systems"))
        symal.add(symposiam("2022 23rd International Symposium on Quality Electronic Design (ISQED)","6 - 7 April 2022  |  Event Format: Virtual","IEEE Circuits and Systems Society; IEEE Electron Devices Society; IEEE Reliability Society; International Society for Quality Electronic Design","Communication, Networking and Broadcast Technologies; Components, Circuits, Devices and Systems; Photonics and Electrooptics"))
        symal.add(symposiam("2022 5th International Symposium on Autonomous Systems (ISAS)","8 - 10 April 2022  |  Hangzhou, China  |  Event Format: Hybrid (In-person and Virtual)"," Hangzhou Innovation Institute, Beihang University; IEEE Computational Intelligence Society; IEEE Industrial Electronics Society; State Key Laboratory of Synthetical Automation for Process Industries, Northeastern University, China; Technical Committee on Dependable Control Systems, Chinese Association of Automation; Technical Committee on Guidance, Navigation, and Control, Chinese Association of Automation"," Aerospace; Components, Circuits, Devices and Systems; Robotics and Control Systems; Signal Processing and Analysis; Transportation"))

        layout.layoutManager=GridLayoutManager(this,1)
        var adapter = symposiamAdapter(this,symal)
        layout.adapter=adapter

    }
}