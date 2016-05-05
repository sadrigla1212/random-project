package medicalcenter;
import medicalcenter.Appointments.Appointments;
import medicalcenter.Time.Time;
import medicalcenter.Appointments.Appointment;
//paspaudus ant appointment texto turi ismeti nauja langa su doctor notes 
//daktaras paspaudes ant appointmento gali prideti notes ir visa kita.
//susikurt nauja langa makeappointment kur bus visas daktaru listas, paspaudus ir galima bus issirinkti daktara


import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import medicalcenter.user.doctor.Doctor;
import medicalcenter.user.patient.Patient;

/**
 *
 * @author Kasparas
 */

public class Interface extends JFrame {
    private int KIEK;
    private int isdoc = 0;
    private int temp;
    private int appdsize;
    private int appdsize1;
    private int docidnumber;
    private int [] idarray;
    private String time;

    private int day;
    private int idp;
    private int size;
    private int dsize;
    private int Mondayis;
    private int tueis;
    private int wedis;
    private int thuris;
    private int friis;
    private int Mondayis813;
    private int tueis813;
    private int wedis813;
    private int thuris813;
    private int friis813;
    private int Mondayis1419;
    private int tueis1419;
    private int wedis1419;
    private int thuris1419;
    private int friis1419;
    private int Hour[];
    private int Week[];
    //Arrays of appointment labels
    private JLabel [] app;
    private JLabel [] date;
    private JLabel [] appd;
    private JLabel [] apph;
    private JLabel [] docl;
    private JLabel [] apphd;
    private JCheckBox[] selecthm = new JCheckBox[5];
    private JCheckBox [] selectht= new JCheckBox[5];
    private JCheckBox [] selecthw= new JCheckBox[5];
    private JCheckBox[] selecthth= new JCheckBox[5];
    private JCheckBox [] selecthf= new JCheckBox[5];
    private String docID;
    private String patientID;
    //objects
    UserManager log = new UserManager();
    Time t = new Time();
    Time apptime = new Time();
    Appointments a = new Appointments();
    Appointment ap = new Appointment();
    
    //Buttons
    private JButton skip10y;
    private JButton skip10m;
    private JButton skip10d;
    private JButton skip10h;
    private JButton skip10mm;
    private JButton skip1y;
    private JButton skip1m;
    private JButton skip1d;
    private JButton skip1h;
    private JButton skip1mm;
    private JButton login;
    private JButton history;
    private JButton register;
    private JButton Schedule;
    private JButton skipweek;
    private JButton skipweekback;
    private JButton registerlead;
    private JButton profile;
    private JButton save;
    private JButton logout;
    private JButton Makeappointment;
    private JButton Pres;
    private JButton submit;
    private JButton Add;
    private JButton Schedulesave;
    private ButtonGroup group;
    //CheckBoxes
    private JCheckBox doc;
    private JCheckBox doc1;
    
    private JCheckBox Monday;
    private JCheckBox Tuesday;
    private JCheckBox Wednesday;
    private JCheckBox Thursday;
    private JCheckBox Friday;
    
    private JCheckBox Monday813;
    private JCheckBox Monday1419;
    private JCheckBox Tuesday813;
    private JCheckBox Tuesday1419;
    private JCheckBox Wednesday813;
    private JCheckBox Wednesday1419;
    private JCheckBox Thursday813;
    private JCheckBox Thursday1419;
    private JCheckBox Friday813;
    private JCheckBox Friday1419;
    
    //Frames
    private JFrame start;
    private JFrame Pmain;
    private JFrame timew;
    private JFrame mak;
    private JFrame Finals;
    private JFrame profileP;
    private JFrame Dmain;
    private JFrame appD;
    private JFrame Shedulemain;
    //textfields usernamel is login username
    private final JTextField DOCcode = new JTextField();
    private final JTextField username = new JTextField(); 
    private final JTextField usernamel = new JTextField();
    private final JTextField name = new JTextField();
    private final JTextField surname = new JTextField(); 
    private final JTextField age = new JTextField();
    private final JTextField inssurance = new JTextField();
    private final JTextField speciality = new JTextField();
    //Login password, and two registration passfields
    private final JPasswordField passl = new JPasswordField();
    private final JPasswordField pass = new JPasswordField();
    private final JPasswordField pass2 = new JPasswordField();
    private final JPasswordField passchange = new JPasswordField();
    private final JPasswordField passchange2 = new JPasswordField();
    
    private JTextArea notearea;
    private JTextArea Paymentarea;
    private JTextArea Prescribtionsarea;
    private JTextField reasonarea;
    private JTextArea Paymentareap;
    private JTextArea noteareap;
    private JTextArea Diagnosisarea;
    
    public Interface () throws IOException{
                log.LoadUsers();
                a.createFiles();
                 t.loadTime();
                 apptime.loadTime();
                 a.loadAppData();
                 startwindow();
         
                 }
    
    private void startwindow (){
        
        start = new JFrame("Clinics");
        start.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel P = new JPanel(new BorderLayout(3,3));
        P.setBorder(new EmptyBorder(80,50,80,50));P.setBackground(Color.white) ;
        
        login = new JButton("Login");
        registerlead = new JButton("Register form");
        
        JPanel Annotation = new JPanel(new GridLayout(1,2));P.add(Annotation, BorderLayout.NORTH);
        JPanel Fields = new JPanel(new GridLayout(3,2));P.add(Fields, BorderLayout.CENTER);
        JPanel Buttons = new JPanel(new GridLayout(2,0));P.add(Buttons, BorderLayout.SOUTH);
        
        Annotation.setBackground(Color.white);
        Fields.setBackground(Color.white);
        Image img = new ImageIcon(this.getClass().getResource("/start.png")).getImage();
        JLabel sticker = new JLabel("");
        
        sticker.setIcon(new ImageIcon(img));
        sticker.setBounds(10, 10, 10, 10);
        Annotation.add(sticker);
        Annotation.add(new JLabel ("Please Log-In or Register down below"));
        Fields.add(new JLabel("Enter your username : "));
        Fields.add(usernamel);
        Fields.add(new JLabel("Enter your password : "));
        Fields.add(passl);
        doc1 = new JCheckBox("Login as a Doctor");doc1.setBackground(Color.white);
        Fields.add(doc1);
        Buttons.add(login);
        Buttons.add(registerlead);
        
        DocLoginCheck ih1 = new DocLoginCheck();
        Handler hnd = new Handler ();
        doc1.addItemListener(ih1);
        login.addActionListener(hnd);
        registerlead.addActionListener(hnd);
        
        start.setResizable(false);
        start.getContentPane().add(P);
        start.setSize(625,550);
        start.setVisible(true);
         }//Starting window
    private void registerwindow(){
        JFrame main = new JFrame("Register Form ");
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel P = new JPanel(new BorderLayout(3,3));
        P.setBorder(new EmptyBorder(50,50,50,50));
        main.setContentPane(P);
        
        JPanel labels = new JPanel(new GridLayout(0,1)); P.add(labels, BorderLayout.WEST);
        JPanel Fields = new JPanel(new GridLayout(0,1)); P.add(Fields, BorderLayout.CENTER);  
        
             
        labels.add(new JLabel("Username: "));
        Fields.add(username);
        labels.add(new JLabel("First Name: "));
        Fields.add(name);
        labels.add(new JLabel("Last Name: "));
        Fields.add(surname);
        labels.add(new JLabel("Password: "));
        Fields.add(pass);
        labels.add(new JLabel("Repeat password: "));
        Fields.add(pass2);
        labels.add(new JLabel("Age: "));
        Fields.add(age);
        labels.add(new JLabel("Inssurance: "));
        Fields.add(inssurance);
        labels.add(new JLabel("Speciality: "));
        Fields.add(speciality); speciality.setEditable(false);
        labels.add(new JLabel(""));
        register = new JButton("Register");
        Fields.add(register);
        doc = new JCheckBox("Register as a Doctor (Code required) ");
        labels.add(doc);
        Fields.add(DOCcode);DOCcode.setVisible(false);
        
        DoccodeCheck ih = new DoccodeCheck();
        Handler hnd1 = new Handler ();
        register.addActionListener(hnd1);
        doc.addItemListener(ih);
        
        main.setSize(550,320);
        main.setResizable(false);
        main.setVisible(true);
        }//Registration window
    
    private void Doctormain() {
        if(log.DoesScheduleExist(usernamel.getText()) == false){
            schedulewindow(false);
        }
        if(log.DoesScheduleExist(usernamel.getText()) == true)  {
            try {
                Week = new int [5];
                Hour = new int [5];
                Week = log.LoadScheduleDays(usernamel.getText());
                Hour = log.LoadScheduleHours(usernamel.getText());
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }               
        }
        try {
            log.LoadUsers();
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
     for (int i=0; i<log.getDoctors().size();i++){
            if(usernamel.getText().equals(log.getDoctors().get(i).getUsername())){
              String n = log.getDoctors().get(i).getName();
              name.setText(n);
              String s = log.getDoctors().get(i).getSurname();
              surname.setText(s); 
              docID = log.getDoctors().get(i).getId();
              surname.setText(s);}}
        
        ap.setisDoc(1);
        Dmain = new JFrame("Doctor. Logged in as: "+usernamel.getText());
        Dmain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("Doc.png"));
        Dmain.add(background);
        background.setLayout(null);
        logout = new JButton("Logout");
        logout.setBounds(380, 350 ,215 ,40); //(x, y, ilgis horizont, vertikal)
        background.add(logout);
        JLabel u = new JLabel(usernamel.getText());
        background.add(u);u.setBounds(35, 50 ,215 ,40);
        JLabel n = new JLabel(name.getText());
        background.add(n);n.setBounds(205, 50 ,215 ,40);
        JLabel s = new JLabel(surname.getText());
        background.add(s);s.setBounds(355, 50 ,215 ,40);
        JLabel id = new JLabel(docID);
        background.add(id);id.setBounds(520, 50 ,215 ,40);
        profile = new JButton("Profile");
        profile.setBounds(380, 300 ,215 ,40); //(x, y, ilgis horizont, vertikal)
        background.add(profile);
        history = new JButton("Appointment History");
        history.setBounds(380, 200 ,215 ,40); 
        background.add(history);
        Schedule = new JButton("Schedule");
        Schedule.setBounds(380, 250 ,215 ,40); //(x, y, ilgis horizont, vertikal)
        background.add(Schedule);
        JLabel Jtime = new JLabel(t.toString());
        Jtime.setBounds(410, 480 ,400 ,40);
        background.add(Jtime);
        
        Handler d = new Handler();
         ArrayList<Appointment> appointment = a.getAllFutureDoctorAppointments(Integer.parseInt(docID), t);
           int v =0;
           String visitedd;
           size = appointment.size();
           appd  = new JLabel [appointment.size()];
           date = new JLabel [appointment.size()];
           idarray = new int [appointment.size()];
        for(int i = 0; i<appointment.size(); i++){
             if (appointment.get(i).isVisited()==true){
        visitedd = "Visited";
        }
        else {
            visitedd = "Not Visited";}
            appointment.get(i).setisDoc(1);
            Patient p = log.getPatient(appointment.get(i).getClientId());
            appd[i] = new JLabel(appointment.get(i).getId()+" | "+p.getName()+" "+p.getSurname() +" | " +visitedd+" | "+appointment.get(i).getReason());
            idarray[i] = appointment.get(i).getClientId();
            date[i] = new JLabel("Date: "+appointment.get(i).getDate()+ " "+appointment.get(i).getTime());
            background.add(appd[i]);
            background.add(date[i]);
            appd[i].setBounds(40, 210+v,260 ,40);
            date[i].setBounds(40, 225+v,260 ,40);
            v = v+45;
            AppointmentsD h= new AppointmentsD();
            appd[i].addMouseListener(h); 
        }
        
        history.addActionListener(d);
        logout.addActionListener(d);
        profile.addActionListener(d);
        Schedule.addActionListener(d);
        Dmain.setSize(615,560);
        Dmain.setResizable(false);
        Dmain.setVisible(true);
        Dmain.setLocationRelativeTo(null);
    }//Main Doctor Window
    private void Patientmain(){
        apptime.setTime(t.getYear(), t.getMonth(), t.getDay(), t.getDayOfWeek(), t.getHour(), t.getMinute());
        isdoc=0;
        try {
            log.LoadUsers();
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        for (int i=0; i<log.getPatients().size();i++){
            if(usernamel.getText().equals(log.getPatients().get(i).getUsername())){
              String n = log.getPatients().get(i).getName();
              name.setText(n);
              String s = log.getPatients().get(i).getSurname();
              surname.setText(s);
              String ag = log.getPatients().get(i).getAge();
              age.setText(ag);
              String o = log.getPatients().get(i).getInsurance();
              inssurance.setText(o);
              patientID = log.getPatients().get(i).getId();         
            }}
        
        Appointment aobj = new Appointment();
        aobj.setisDoc(0);
        Pmain = new JFrame("Patient. Logged in as: "+usernamel.getText());
        Pmain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("Pat.png"));
        Pmain.add(background);
        background.setLayout(null);
        
        logout = new JButton("Logout");
        logout.setBounds(380, 350 ,215 ,40); 
        background.add(logout);
        profile = new JButton("Profile");
        profile.setBounds(380, 300 ,215 ,40); 
        background.add(profile);
        Pres = new JButton("Prescribtions");
        Pres.setBounds(380, 250 ,215 ,40);
        background.add(Pres);
        Makeappointment = new JButton("Make an Appointment");
        Makeappointment.setBounds(380, 150 ,215 ,40); 
        background.add(Makeappointment);
        history = new JButton("Appointment History");
        history.setBounds(380, 200 ,215 ,40); 
        background.add(history);
        
        JLabel u = new JLabel(usernamel.getText());
        background.add(u);u.setBounds(35, 45 ,215 ,40);
        JLabel n = new JLabel(name.getText());
        background.add(n);n.setBounds(145, 45 ,215 ,40);
        JLabel s = new JLabel(surname.getText());
        background.add(s);s.setBounds(225, 45 ,215 ,40);
        JLabel ag = new JLabel(age.getText());
        background.add(ag);ag.setBounds(322, 45 ,215 ,40);
        JLabel o = new JLabel(inssurance.getText());
        background.add(o);o.setBounds(385, 45 ,215 ,40);
        JLabel q = new JLabel(patientID);
        background.add(q);q.setBounds(540, 45 ,215 ,40);
        
        
        
           ArrayList<Appointment> appointment = a.getAllFutureClientAppointments(Integer.parseInt(patientID), t);
           int v =0;
           size = appointment.size();
           app  = new JLabel [appointment.size()];
           date = new JLabel [appointment.size()];
           String visitedd;
            for(int i = 0; i<appointment.size(); i++){
                if (appointment.get(i).isVisited()==true){
        visitedd = "Visited";
        }
        else {
            visitedd = "Not Visited";}
                appointment.get(i).setisDoc(0);
                Doctor d = log.getDoctor(appointment.get(i).getDoctorId());
            app[i] = new JLabel(appointment.get(i).getId()+" | "+d.getName()+" "+d.getSurname() +" | " +visitedd+" | "+appointment.get(i).getReason());
            date[i] = new JLabel("Date: "+appointment.get(i).getDate()+ " "+appointment.get(i).getTime());
            background.add(app[i]);
            background.add(date[i]);
            app[i].setBounds(45, 210+v,250 ,40);
            date[i].setBounds(45, 225+v,250 ,40);
            v = v+45;
            AppointmentsL m = new AppointmentsL();
            app[i].addMouseListener(m);}
 
        JLabel Jtime = new JLabel(t.toString());
        Jtime.setBounds(410, 480 ,400 ,40);
        background.add(Jtime);
        
        Handler c = new Handler();
        logout.addActionListener(c);
        profile.addActionListener(c);
        Pres.addActionListener(c);
        history.addActionListener(c);
        Makeappointment.addActionListener(c);
        Pmain.setSize(615,560);
        Pmain.setLocationRelativeTo(null);
        Pmain.setResizable(false);
        Pmain.setVisible(true);
    }//Main Patient Window
    private void Historywindow(){
        JFrame main = new JFrame("History");
        main.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("hist.png"));
        main.add(background);
        background.setLayout(null); 
        
        if (isdoc==0){
        ArrayList<Appointment> appointment = a.getAllClientAppointments(Integer.parseInt(patientID));
        appdsize = appointment.size();
        int v =0;
        apph=new JLabel[appdsize];
        for(int i = 0; i<appointment.size(); i++){
            Doctor d = log.getDoctor(appointment.get(i).getDoctorId());
            apph[i] = new JLabel(appointment.get(i).getId()+" | "+d.getName()+" "+d.getSurname() +" | "+appointment.get(i).getReason());
            background.add(apph[i]);
            apph[i].setBounds(70, 65+v,215 ,25);
            Historylistener h = new Historylistener();
            apph[i].addMouseListener(h);
            v = v+20;}}
        if (isdoc!=0){
         ArrayList<Appointment> appointment = a.getAllDoctorAppointments(Integer.parseInt(docID));
        int v =0;
        appdsize1 = appointment.size();
        apphd=new JLabel[appdsize1];
        for(int i = 0; i<appointment.size(); i++){
            
            apphd[i] = new JLabel(appointment.get(i).toString());
            background.add(apphd[i]);
            apphd[i].setBounds(70, 65+v,215 ,25);
            Historylistener h = new Historylistener();
            apphd[i].addMouseListener(h);
            v = v+20;}}   
        
        main.setSize(350,580);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setVisible(true);
    }//Appointment History window
    private void profilewindow(){
        
        profileP = new JFrame("Profile");
        profileP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel P = new JPanel(new BorderLayout(3,3));
        P.setBorder(new EmptyBorder(40,40,40,40));
        P.setBackground(Color.WHITE);
        JPanel labels = new JPanel(new GridLayout(0,1));
        labels.setBackground(Color.WHITE);
        JPanel controls = new JPanel(new GridLayout(0,1));
        P.add(labels, BorderLayout.WEST);
        P.add(controls, BorderLayout.CENTER);
        labels.add(new JLabel("Username: "));
        controls.add(usernamel);usernamel.setEditable(false);
        labels.add(new JLabel("First Name: "));
        controls.add(name); 
        labels.add(new JLabel("Last Name: "));
        controls.add(surname);
        if(isdoc==0){
        labels.add(new JLabel("Inssurance: "));
        controls.add(inssurance);
        labels.add(new JLabel("Age: "));
        controls.add(age);
        }
        labels.add(new JLabel("Old Password: "));
        controls.add(passl);
        labels.add(new JLabel("New Password: "));
        controls.add(passchange);
        labels.add(new JLabel("Repeat New Password: "));
        controls.add(passchange2);
        labels.add(new JLabel(""));
        save = new JButton ("Save Changes");
        controls.add(save);
        Handler h = new Handler();
        save.addActionListener(h);
        profileP.setResizable(false);
        profileP.getContentPane().add(P);
        profileP.setSize(550,320);
        profileP.setLocationRelativeTo(null);
        profileP.setVisible(true);
    }//Edit profile window
    private void preswindow(){
        ArrayList<Appointment> appointment = a.getAllClientAppointments(Integer.parseInt(patientID));
        JFrame pres = new JFrame(usernamel.getText()+ " prescribtions");
        pres.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("Pres.png"));
        int v=0;
        for(int i=0;i<appointment.size();i++){
        JTextArea pr = new JTextArea(appointment.get(i).getPrescriptions().get(0).getName());
        JTextArea pr1 = new JTextArea(appointment.get(i).getPrescriptions().get(0).getExpireDate());
        JTextArea pr2 = new JTextArea(appointment.get(i).getPrescriptions().get(0).getPayment());
        background.add(pr);
        background.add(pr1);
        background.add(pr2);
        pr.setBounds(25, 50+v, 100, 15);
        pr.setEditable(false);
        pr.setOpaque(false);
        pr1.setBounds(130, 50+v, 100, 15);
        pr1.setEditable(false);
        pr1.setOpaque(false);
        pr2.setBounds(325, 50+v, 100, 15);
        pr2.setEditable(false);
        pr2.setOpaque(false);
        v=v+25;
        }
        
        pres.add(background);
        background.setLayout(null);
        pres.setSize(420,320);
        pres.setResizable(false);
        pres.setVisible(true);
    }//Prescribtion window
    private void timewindow(){
        timew = new JFrame("Time");
        timew.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel P = new JPanel(new BorderLayout(5,5));
        P.setBorder(new EmptyBorder(25,25,25,25));
        P.setBackground(Color.WHITE);
        JPanel Fields = new JPanel(new GridLayout(5,3));
        P.add(Fields, BorderLayout.CENTER);
        Fields.setBackground(Color.WHITE);
        
        skip10y = new JButton("Skip 10");
        skip10m = new JButton("Skip 10");
        skip10d = new JButton("Skip 10");
        skip10h = new JButton("Skip 10");
        skip10mm = new JButton("Skip 10");
        skip1y = new JButton("Skip 1");
        skip1m = new JButton("Skip 1");
        skip1d = new JButton("Skip 1");
        skip1h = new JButton("Skip 1");
        skip1mm = new JButton("Skip 1");
        
        
        Fields.add(new JLabel("Year:"));
        Fields.add(skip10y);
        Fields.add(skip1y);
        Fields.add(new JLabel("Month: "));
        Fields.add(skip10m); 
        Fields.add(skip1m);
        Fields.add(new JLabel("Day: "));
        Fields.add(skip10d);
        Fields.add(skip1d);
        Fields.add(new JLabel("Hours: "));
        Fields.add(skip10h);
        Fields.add(skip1h);
        Fields.add(new JLabel("Minutes: "));
        Fields.add(skip10mm);
        Fields.add(skip1mm);
        
        Handler t = new Handler();
        skip1y.addActionListener(t);
        skip1m.addActionListener(t);
        skip1d.addActionListener(t);
        skip1h.addActionListener(t);
        skip1mm.addActionListener(t);
        skip10y.addActionListener(t);
        skip10m.addActionListener(t);
        skip10d.addActionListener(t);
        skip10h.addActionListener(t);
        skip10mm.addActionListener(t);
         
        timew.setResizable(false);
        timew.getContentPane().add(P);
        timew.pack();
        timew.setLocation(1000,300);
        timew.setVisible(true);
    }//Timewindow
    private void appointmentwindow(int c, boolean ispassed){
        ArrayList<Appointment> appointment;
        if(ispassed==false){
        appointment = a.getAllFutureClientAppointments(Integer.parseInt(patientID), t);}
        else{
          appointment = a.getAllClientAppointments(Integer.parseInt(patientID));  
        }
        String g = appointment.get(c).toString();
        JFrame appat = new JFrame(g);
        appat.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("Patientap.png"));
        
        try {
            a.loadDiagnosis();
            a.loadNotes();
        } catch (IOException ex) {
            Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        noteareap = new JTextArea(appointment.get(c).getDiagnosis());
        background.add(noteareap);
        noteareap.setBounds(25, 65, 370, 128);
        noteareap.setEditable(false);
        noteareap.setOpaque(false);
        Paymentareap = new JTextArea(appointment.get(c).getPayment());
        background.add(Paymentareap);
        Paymentareap.setBounds(25, 255, 370, 128);
        Paymentareap.setEditable(false);
        Paymentareap.setOpaque(false); 
        
        appat.add(background);
        background.setLayout(null);
        
        
       appat.setSize(430,400);
        appat.setResizable(false);
        appat.setVisible(true);   
    }//Appointment window for patient
    private void appointmentwindowd(int c, int num, boolean ispassed){
        
        
        
        Patient p = log.getPatient(num);
        String ag = p.getAge();
        String i = p.getInsurance();
        String s = p.getSurname();
        String n = p.getName();
        ArrayList<Appointment> appointment;
        if (ispassed==false){
        appointment = a.getAllFutureDoctorAppointments(Integer.parseInt(docID), t);}
        else {
        appointment = a.getAllDoctorAppointments(Integer.parseInt(docID));}
         
        String g = appointment.get(c).toString();
        String r = appointment.get(c).getReason();
        appD = new JFrame(g);
        appD.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("doctorap.png"));
        Prescribtionsarea = new JTextArea();
        background.add(Prescribtionsarea);
        Prescribtionsarea.setBounds(22, 241, 373, 70); 
        Diagnosisarea = new JTextArea();
        background.add(Diagnosisarea);
        Diagnosisarea.setBounds(445, 235, 253, 115);  
        Paymentarea = new JTextArea();
        background.add(Paymentarea);
        Paymentarea.setBounds(22, 368, 373, 33);
        notearea = new JTextArea();
        background.add(notearea);
        notearea.setBounds(25, 51, 362, 128);
        
        
        if(appointment.get(c).isVisited())
        {
          Diagnosisarea.setText(appointment.get(c).getDiagnosis());
          Paymentarea.setText(appointment.get(c).getPayment());
          notearea.setText(appointment.get(c).getNotes());
          
        }
        else {
          
            appointment.get(c).setVisited(true);
         
        }
        
        appD.add(background);
        background.setLayout(null);
        Handler ob = new Handler();
        Add = new JButton("Save Changes");
        background.add(Add).setBounds(438, 375, 265, 30);
        Add.addActionListener(ob);
        
        if(ispassed==true){
          Prescribtionsarea.setEditable(false);Prescribtionsarea.setOpaque(false);
          Diagnosisarea.setEditable(false);Diagnosisarea.setOpaque(false);
          Paymentarea.setEditable(false);Paymentarea.setOpaque(false);
          notearea.setEditable(false); notearea.setOpaque(false);
          Add.setVisible(false);
        }
        background.add(new JLabel("First Name: ")).setBounds(460, 40, 110, 20);
        background.add(new JLabel(n)).setBounds(560, 40, 110, 20);
        background.add(new JLabel("Last Name: ")).setBounds(460, 65, 110, 20);
        background.add(new JLabel(s)).setBounds(560, 65, 110, 20);
        background.add(new JLabel("Inssurance: ")).setBounds(460, 90, 110, 20);
        background.add(new JLabel(i)).setBounds(560, 90, 110, 20);
        background.add(new JLabel("Age: ")).setBounds(460, 115, 110, 20);
        background.add(new JLabel(ag)).setBounds(560, 115, 110, 20);
         background.add(new JLabel("Reason: ")).setBounds(460, 140, 110, 20);
        background.add(new JLabel(r)).setBounds(560, 140, 110, 20);
        
        appD.setSize(732,445);
        appD.setResizable(false);
        appD.setVisible(true);   
    }//Appointment window for doctor
    private void makeappointment () {
              
        ArrayList<Doctor> Doclist = log.getDoctors();
       
        mak = new JFrame("Arrange a visit");
        mak.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("dd.png"));
        
            int v =0;
            
            dsize = Doclist.size();   
            docl = new JLabel[Doclist.size()];
            for(int i = 0; i<dsize; i++){
                
            docl[i] = new JLabel("Doctor: " + Doclist.get(i).getName() +" "+ Doclist.get(i).getSurname() + " | Specialist: "+ Doclist.get(i).getSpeciality());
            background.add(docl[i]);
            docl[i].setBounds(50, 120+v, 330, 20);
            v = v+35;
            Doctorlist m = new Doctorlist();
            docl[i].addMouseListener(m);
             
            }   
                
        mak.add(background);
        background.setLayout(null);
        mak.setSize(450,480);
        mak.setResizable(false);
        mak.setVisible(true);  
    }//Make and appointment window
    private void finalregisterwindow (int c){
        
        ArrayList<Doctor> Doclist = log.getDoctors();
        if(log.DoesScheduleExist(Doclist.get(c).getUsername()) == true)  {
            try {
                Week = new int [5];
                Hour = new int [5];
                Week = log.LoadScheduleDays(Doclist.get(c).getUsername());
                Hour = log.LoadScheduleHours(Doclist.get(c).getUsername());
            } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }               
        } 
        
        temp = c;
        Finals = new JFrame("Arrange a visit");
        Finals.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("final.png"));
        JLabel dn = docl[c];
        background.add(dn);
        dn.setBounds(52, 20, 330, 60);
        docidnumber = c;
        reasonarea = new JTextField();
        background.add(reasonarea);
        reasonarea.setBounds(20, 140, 375, 118);
        
        submit = new JButton("Submit");
        background.add(submit);
        submit.setBounds(20, 268, 375, 30);
        skipweek = new JButton ("Skip Week>");
                background.add(skipweek);
        skipweek.setBounds(590, 275, 120, 30);
         skipweekback = new JButton ("<Skip Back");
                background.add(skipweekback);
        skipweekback.setBounds(460, 275, 120, 30);
        
        JLabel timeforapp = new JLabel(apptime.toString());
        timeforapp.setBounds(500, 322, 375, 30);
        background.add(timeforapp);
        Handler ob = new Handler();
        submit.addActionListener(ob);
        skipweek.addActionListener(ob);
        skipweekback.addActionListener(ob);
        
        
        
        int kiek;
        int v=0;
        
        if((Week[0]==1)&&(Hour[0]==8)){
            
        for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthm[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthm[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthm[i].setBounds(469, 60+v, 39, 20);
        background.add(selecthm[i]);
         v=v+35;
        Choosetime ch = new Choosetime();
        selecthm[i].addItemListener(ch);
        }}
       
        if((Week[0]==1)&&(Hour[0]==14)){
            
        for(int i=0; i<5;i++){
            kiek = i+14;
            if(i==0){
        selecthm[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthm[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthm[i].setBounds(469, 60+v, 39, 20);
        background.add(selecthm[i]);
         v=v+35;
         Choosetime ch = new Choosetime();
        selecthm[i].addItemListener(ch);
        }}
        v=0;
        if(Week[0]==0){
            for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthm[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthm[i] = new JCheckBox(Integer.toString(kiek));  
             selecthm[i].setBounds(469, 60+v, 39, 20);
        background.add(selecthm[i]);selecthm[i].setVisible(false);
          }  
        }}
    ////////////////////////////////////////////////////////////////////////////////////    
        if((Week[1]==1)&&(Hour[1]==8)){
           
        for(int i=0; i<5;i++){
            kiek = i+8;
            
            if(i==0){
        selectht[i] = new JCheckBox(Integer.toString(kiek));
       
        
            }
          if(i!=0){
             selectht[i] = new JCheckBox(Integer.toString(kiek));  
             
          }  
            selectht[i].setBounds(515, 60+v, 40, 20);
       
        background.add(selectht[i]);
         v=v+35;
        
        Choosetime ch = new Choosetime();
        selectht[i].addItemListener(ch);}}
    
        
        
        if((Week[1]==1)&&(Hour[1]==14)){
            
        for(int i=0; i<5;i++){
            kiek = i+14;
            if(i==0){
        selectht[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selectht[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selectht[i].setBounds(515, 60+v, 40, 20);
        background.add(selectht[i]);
         v=v+35;
        
        
          Choosetime ch = new Choosetime();
        selectht[i].addItemListener(ch);}}v=0;
        
        if(Week[1]==0){
            for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selectht[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selectht[i] = new JCheckBox(Integer.toString(kiek));  
             selectht[i].setBounds(515, 60+v, 40, 20);
          }  background.add(selectht[i]);selectht[i].setVisible(false);
        }}
        ///////////////////////////////////////////////////////////////
                if((Week[2]==1)&&(Hour[2]==8)){
            
        for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthw[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthw[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthw[i].setBounds(560, 60+v, 40, 20);
        background.add(selecthw[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthw[i].addItemListener(ch);
        }}
        
        if((Week[2]==1)&&(Hour[2]==14)){
           
        for(int i=0; i<5;i++){
            kiek = i+14;
            if(i==0){
        selecthw[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthw[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthw[i].setBounds(560, 60+v, 40, 20);
        background.add(selecthw[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthw[i].addItemListener(ch);
        }}v=0;
        
        if(Week[2]==0){
            for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthw[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthw[i] = new JCheckBox(Integer.toString(kiek));  
          }  selecthw[i].setBounds(560, 60+v, 40, 20);
        background.add(selecthw[i]);selecthw[i].setVisible(false);
        }}
        ///////////////////////////////////////////
                        if((Week[3]==1)&&(Hour[3]==8)){
            
        for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthth[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthth[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthth[i].setBounds(605, 60+v, 40, 20);
        background.add(selecthth[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthth[i].addItemListener(ch);
        }}
        
        if((Week[3]==1)&&(Hour[3]==14)){
            
        for(int i=0; i<5;i++){
            kiek = i+14;
            if(i==0){
        selecthth[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthth[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthth[i].setBounds(605, 60+v, 40, 20);
        background.add(selecthth[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthth[i].addItemListener(ch);
        }}v=0;
        
        if(Week[3]==0){
            for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthth[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthth[i] = new JCheckBox(Integer.toString(kiek));  
          }  selecthth[i].setBounds(605, 60+v, 40, 20);
        background.add(selecthth[i]);selecthth[i].setVisible(false);
        }}
        ////////////////////////////////////////////////////////////////////
                        if((Week[4]==1)&&(Hour[4]==8)){
            
        for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthf[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthf[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthf[i].setBounds(650, 60+v, 40, 20);
        background.add(selecthf[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthf[i].addItemListener(ch);
        }}
        
        if((Week[4]==1)&&(Hour[4]==14)){
            
        for(int i=0; i<5;i++){
            kiek = i+14;
            if(i==0){
        selecthf[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthf[i] = new JCheckBox(Integer.toString(kiek));  
          }  
            selecthf[i].setBounds(650, 60+v, 40, 20);
        background.add(selecthf[i]);
         v=v+35;
          Choosetime ch = new Choosetime();
        selecthf[i].addItemListener(ch);
        }}if(Week[4]==0){
            for(int i=0; i<5;i++){
            kiek = i+8;
            if(i==0){
        selecthf[i] = new JCheckBox(Integer.toString(kiek));
            }
          if(i!=0){
             selecthf[i] = new JCheckBox(Integer.toString(kiek));  
          }  
          selecthf[i].setBounds(650, 60+v, 40, 20);
        background.add(selecthf[i]);selecthf[i].setVisible(false);
        }}
        
        
        Finals.add(background);
        background.setLayout(null);
        Finals.setSize(733,390);
        Finals.setResizable(false);
        Finals.setVisible(true);  
    }//Window taht pops when you select a doctor in "make an appontment"
    private void schedulewindow(boolean x){
        Shedulemain = new JFrame(usernamel.getText()+ " FIRST LOGIN!");
        Shedulemain.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JLabel background=new JLabel(new ImageIcon("Schedule.png"));
       
        
        
        if(x==false){
            
        Monday = new JCheckBox("Monday");Monday.setBounds(25, 70, 80, 25);
        background.add(Monday);
        Tuesday = new JCheckBox("Tuesday");Tuesday.setBounds(110, 70, 80, 25);
        background.add(Tuesday);
        Wednesday = new JCheckBox("Wednesday");Wednesday.setBounds(195, 70, 100, 25);
        background.add(Wednesday);
        Thursday = new JCheckBox("Thursday");Thursday.setBounds(300, 70, 95, 25);
        background.add(Thursday);
        Friday = new JCheckBox("Friday");Friday.setBounds(400, 70, 95, 25);
        background.add(Friday);
       
        Monday813 = new JCheckBox("8h-13h");Monday813.setBounds(25, 100, 80, 25);
        background.add(Monday813);Monday813.setEnabled(false);
        Monday1419 = new JCheckBox("14h-19h");Monday1419.setBounds(25, 130, 80, 25);
        background.add(Monday1419);Monday1419.setEnabled(false);
        
        Tuesday813 = new JCheckBox("8h-13h");Tuesday813.setBounds(110, 100, 80, 25);
        background.add(Tuesday813);Tuesday813.setEnabled(false);
        Tuesday1419 = new JCheckBox("14h-19h");Tuesday1419.setBounds(110, 130, 80, 25);
        background.add(Tuesday1419);Tuesday1419.setEnabled(false);
        
        Wednesday813 = new JCheckBox("8h-13h");Wednesday813.setBounds(195, 100, 100, 25);
        background.add(Wednesday813);Wednesday813.setEnabled(false);
        Wednesday1419 = new JCheckBox("14h-19h");Wednesday1419.setBounds(195, 130, 100, 25);
        background.add(Wednesday1419);Wednesday1419.setEnabled(false);
        
        Thursday813 = new JCheckBox("8h-13h");Thursday813.setBounds(300, 100, 95, 25);
        background.add(Thursday813);Thursday813.setEnabled(false);
        Thursday1419 = new JCheckBox("14h-19h");Thursday1419.setBounds(300, 130, 95, 25);
        background.add(Thursday1419);Thursday1419.setEnabled(false);
        
        Friday813 = new JCheckBox("8h-13h");Friday813.setBounds(400, 100, 95, 25);
        background.add(Friday813);Friday813.setEnabled(false);
        Friday1419 = new JCheckBox("14h-19h");Friday1419.setBounds(400, 130, 95, 25);
        background.add(Friday1419);Friday1419.setEnabled(false);
        Schedulesave = new JButton("Save Schedule");
        Schedulesave.setBounds(10, 215, 494, 30);
        background.add(Schedulesave);ScheduleL il = new ScheduleL();
        Handler h = new Handler();
        Schedulesave.addActionListener(h);
        Monday.addItemListener(il);
        Tuesday.addItemListener(il);
        Wednesday.addItemListener(il);
        Thursday.addItemListener(il);
        Friday.addItemListener(il);
        Monday813.addItemListener(il);
        Tuesday813.addItemListener(il);
        Wednesday813.addItemListener(il);
        Thursday813.addItemListener(il);
        Friday813.addItemListener(il);
        Monday1419.addItemListener(il);
        Tuesday1419.addItemListener(il);
        Wednesday1419.addItemListener(il);
        Thursday1419.addItemListener(il);
        Friday1419.addItemListener(il);}
        
        
         if(x==true){
                            Shedulemain = new JFrame(usernamel.getText()+ " Schedule");
                            background=new JLabel(new ImageIcon("Schedule2.png"));
                            Shedulemain.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            if((Week[0]==1)&&(Hour[0]==8))
                            {
                                background.add(new JLabel("Monday: from 8:00 to 13:00 hour")).setBounds(25, 70, 220, 25);
                            }
                             if((Week[0]==1)&&(Hour[0]==14))
                            {
                                background.add(new JLabel("Monday: from 14:00 to 19:00 hour")).setBounds(25, 70, 220, 25);
                            }
                              if((Week[1]==1)&&(Hour[1]==8))
                            {
                                background.add(new JLabel("Tuesday: from 8:00 to 13:00 hour")).setBounds(25, 100, 220, 25);
                            }
                                if((Week[1]==1)&&(Hour[1]==14))
                            {
                                background.add(new JLabel("Tuesday: from 14:00 to 19:00 hour")).setBounds(25, 100, 220, 25);
                            }
                                 if((Week[2]==1)&&(Hour[2]==8))
                            {
                                background.add(new JLabel("Wednesday: from 8:00 to 13:00 hour")).setBounds(25, 130, 220, 25);
                            }
                             if((Week[2]==1)&&(Hour[2]==14))
                            {
                                background.add(new JLabel("Wednesday: from 14:00 to 19:00 hour")).setBounds(25, 130, 220, 25);
                            }
                              if((Week[3]==1)&&(Hour[3]==8))
                            {
                                background.add(new JLabel("Thursday: from 8:00 to 13:00 hour")).setBounds(25, 160, 220, 25);
                            }
                                if((Week[3]==1)&&(Hour[3]==14))
                            {
                                background.add(new JLabel("Thursday: from 14:00 to 19:00 hour")).setBounds(25, 160, 220, 25);
                            }
                                   if((Week[4]==1)&&(Hour[4]==8))
                            {
                                background.add(new JLabel("Friday: from 8:00 to 13:00 hour")).setBounds(25, 190, 220, 25);
                            }
                                if((Week[4]==1)&&(Hour[4]==14))
                            {
                                background.add(new JLabel("Friday: from 14:00 to 19:00 hour")).setBounds(25, 190, 220, 25);
                            }
                                
                                
                                    if(Week[0]==0)
                            {
                                background.add(new JLabel("Monday: Free Day")).setBounds(25, 70, 180, 25);
                            }
                                        if(Week[1]==0)
                            {
                                background.add(new JLabel("Tuesday: Free Day")).setBounds(25, 100, 180, 25);
                            }
                                            if(Week[2]==0)
                            {
                                background.add(new JLabel("Wednesday: Free Day")).setBounds(25, 130, 180, 25);
                            }
                                                if(Week[3]==0)
                            {
                                background.add(new JLabel("Thursday: Free Day")).setBounds(25, 160, 180, 25);
                            }
                                                    if(Week[4]==0)
                            {
                                background.add(new JLabel("Friday: Free Day")).setBounds(25, 190, 180, 25);
                            }
        }
              Shedulemain.add(background);
        background.setLayout(null);
        Shedulemain.setSize(520,320);
        Shedulemain.setResizable(false);
        Shedulemain.setVisible(true);
          
        
    }
    
    private class Handler implements ActionListener{
      @Override
      public void actionPerformed (ActionEvent event) {
          if(event.getSource()==Schedule){
            schedulewindow(true);  
          }
          if(event.getSource()==skipweekback){
             apptime.setTime(t.getYear(), t.getMonth(), t.getDay(), t.getDayOfWeek(), t.getHour(), t.getMinute());
             Finals.dispose();
             finalregisterwindow(temp);
          }
          if(event.getSource()==skipweek){
             apptime.addDay(7);
             Finals.dispose();
             finalregisterwindow(temp);
          }
          if(event.getSource()==Schedulesave){
              Week = new int[5];
              Hour = new int[5];
              if(Mondayis==1){Week[0]=1;
                  if(Mondayis813==1){
                      Hour[0]=8;
                  }
                  if(Mondayis1419==1){
                      Hour[0]=14;
                  }
                  
              }
              if(tueis==1){Week[1]=1;
                  if(tueis813==1){
                      Hour[1]=8;
                  }
                  if(tueis1419==1){
                      Hour[1]=14;
                  }
                  
              }
              
              if(wedis==1){Week[2]=1;
                  if(wedis813==1){
                      Hour[2]=8;
                  }
                  if(wedis1419==1){
                      Hour[2]=14;
                  }
                 
              }
              if(thuris==1){Week[3]=1;
                  if(thuris813==1){
                      Hour[3]=8;
                  }
                  if(thuris1419==1){
                      Hour[3]=14;
                  }
                  
              }
              if(friis==1){Week[4]=1;
                  if(friis813==1){
                      Hour[4]=8;
                  }
                  if(friis1419==1){
                      Hour[4]=14;
                  }
                  
              }
              
              
              
             JOptionPane.showMessageDialog(null, "Saved!"); 
              try {
                  log.SaveSchedule(usernamel.getText(), Week, Hour);
              } catch (IOException ex) {
                  Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
              }
             Shedulemain.dispose();
          }
          if(event.getSource()==submit){
           if (KIEK!=0){
               ap = new Appointment();
           }
            ap.setClientId(Integer.parseInt(patientID));
            ap.setDoctorId(Integer.parseInt(log.getDoctors().get(docidnumber).getId()));
            ap.setReason(reasonarea.getText());
            apptime.addDay(day);
            
            ap.setDate(apptime.getDate());
            
            ap.setTime(time+":00");
            a.addAppointment(ap);
            
            time="";
            day=0;
            JOptionPane.showMessageDialog(null, "Appointment Registered!");
            
            Finals.dispose();
            Pmain.dispose();
            Patientmain();
            KIEK++;
            
          }
          
          if(event.getSource()==Makeappointment){
              makeappointment();
          }
          
          if (event.getSource()==Add){
           ap.setNotes(notearea.getText());
           ap.addPrescription("2017-05-26","16eur",Prescribtionsarea.getText());
           ap.setPayment(Paymentarea.getText());
           ap.setDiagnosis(Diagnosisarea.getText());
           a.editAppointment(ap);
           JOptionPane.showMessageDialog(null, "Saved!");
           appD.dispose();
              try {
                  a.saveAppointments();
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
              }
          }

            

if(event.getSource()==login) {
         String Log = "Success! You have loged in.";
         char pwd[] = passl.getPassword();
         String pwd1 = new String(pwd);
         if(isdoc!=0){
            try {
               String msg = log.LogIn(usernamel.getText(), pwd1, true);
               JOptionPane.showMessageDialog(null, msg);
               if (msg.equals(Log)){
                 start.dispose();
                 passl.setText(null);
                
                 timewindow();
                 Doctormain(); 
                 
               }
               
             } catch (IOException ex) {
                Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
        else try {
            String msg = log.LogIn(usernamel.getText(), pwd1, false);
             JOptionPane.showMessageDialog(null, msg );
             if (msg.equals(Log)){
                 start.dispose();
                 passl.setText(null);
                 
                 timewindow();
                 Patientmain();
                 
               }
             
         } catch (IOException ex) {
             Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
         }}
         
if(event.getSource()==registerlead) {registerwindow();}
if(event.getSource()==logout){
            startwindow();
            username.setText(null);
               name.setText(null);
               surname.setText(null);
               pass.setText(null);
               pass2.setText(null);
               age.setText(null);
               
               passl.setText(null);
               inssurance.setText(null);
            usernamel.setEditable(true);
            
              try {
                  t.saveTime();
                  a.saveAppointments();
              } catch (FileNotFoundException ex) {
                  Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
              }
            if (isdoc==0){
             Pmain.dispose(); 
             timew.dispose();
            }
            if(isdoc==1){
            Dmain.dispose();
            timew.dispose();
            isdoc=0;
            }
        }
if(event.getSource()==register){UserManager reg = new UserManager();
         char pwd[] = pass.getPassword();
         String pwd1 = new String(pwd);
         char pwd2[] = pass2.getPassword();
         String Pwd2 = new String(pwd2);
         
         if (DOCcode.getText().equals("")){
           try {
              String msg = reg.Register(name.getText(), surname.getText(), username.getText(), pwd1, Pwd2, age.getText(), inssurance.getText(),speciality.getText(), false, DOCcode.getText());
               JOptionPane.showMessageDialog(null, msg);
               username.setText(null);
               usernamel.setText(null);
               name.setText(null);
               surname.setText(null);
               pass.setText(null);
               pass2.setText(null);
               passl.setText(null);
               age.setText(null);
               inssurance.setText(null);
               speciality.setText(null);
               DOCcode.setText(null);
               log.LoadUsers();
               
               
           } catch (IOException ex) {
               Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
           }
}   else try {
    String msg = reg.Register(name.getText(), surname.getText(), username.getText(), pwd1, Pwd2, age.getText(), inssurance.getText(), speciality.getText(),true, DOCcode.getText());
    JOptionPane.showMessageDialog(null, msg);
    username.setText(null);
    usernamel.setText(null);
               name.setText(null);
               surname.setText(null);
               pass.setText(null);
               pass2.setText(null);
               speciality.setText(null);
               DOCcode.setText(null);  
               log.LoadUsers();
       } catch (IOException ex) {
           Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);}}
        
if(event.getSource()==profile){
           profilewindow(); 
        } 
if(event.getSource()==save){
            if (isdoc==0){
             
                try {
                    String c = log.EditUser(usernamel.getText(), name.getText(), surname.getText(), age.getText(),inssurance.getText() ,passl.getText(),passchange.getText(),passchange2.getText(), false);
                    JOptionPane.showMessageDialog(null, c);
                    passl.setText(null);
                    passchange.setText(null);
                    passchange2.setText(null);
                    Pmain.dispose();
                    profileP.dispose();
                    Patientmain();
 
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                     }
            
            if (isdoc==1){
             
                try {
                    String c= log.EditUser(usernamel.getText(), name.getText(), surname.getText(), age.getText(),inssurance.getText() ,passl.getText(),passchange.getText(),passchange2.getText(), true);
                    JOptionPane.showMessageDialog(null, c);
                    passl.setText(null);
                    passchange.setText(null);
                    passchange2.setText(null);
                    profileP.dispose();
                    Dmain.dispose();
                    Doctormain(); 
                    
                } catch (IOException ex) {
                    Logger.getLogger(Interface.class.getName()).log(Level.SEVERE, null, ex);
                }
                     }
            
        } 
if(event.getSource()==Pres){
   ArrayList<Appointment> appointment = a.getAllClientAppointments(Integer.parseInt(patientID));
   for(int i =0; i<appointment.size(); i++){
             if(appointment.get(i).getPrescriptions().isEmpty()){
                 if (i==appointment.size()-1){
                   JOptionPane.showMessageDialog(null, "No Prescribtions Added!");  
                 }}
             else {
                 //userio objektas, kuriam siunciamas ID ir jis turi metoda get presc
                preswindow(); 
              }}
         }
         if(event.getSource()==history){
            Historywindow();
        }
       if(event.getSource()==skip10y) {
          t.addYear(10);
          if (isdoc==0){
              
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip10m) {
          t.addMonth(10);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip10d) {
          t.addDay(10);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip10h) {
          t.addHour(10);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip10mm) {
          t.addMinute(10);   
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip1y) {
          t.addYear(1);
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip1m) {
          t.addMonth(1);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip1d) {
          t.addDay(1); 
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip1h) {
          t.addHour(1);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
         if(event.getSource()==skip1mm) {
          t.addMinute(1);  
          if (isdoc==0){
          Pmain.dispose();
          Patientmain();}
          if (isdoc!=0){
          Dmain.dispose();
          Doctormain();}
         }
      
      }}
    private class DoccodeCheck implements ItemListener{
        @Override
        public void itemStateChanged (ItemEvent event){
            if(doc.isSelected()){
                DOCcode.setVisible(true);
                age.setEditable(false);
                inssurance.setEditable(false);
                speciality.setEditable(true);
            }
            else{
            DOCcode.setVisible(false);
                age.setEditable(true);
                inssurance.setEditable(true);
            speciality.setEditable(false);}}}
    private class DocLoginCheck implements ItemListener{
        @Override
        public void itemStateChanged (ItemEvent event){
            if(doc1.isSelected()){
                isdoc=1; 
                
            }
            else isdoc=0;
        }
}
    private class AppointmentsL implements MouseListener{
        @Override
        public void mouseClicked (MouseEvent event){
            ArrayList<Appointment> appointment = a.getAllFutureClientAppointments (Integer.parseInt(patientID), t);
            for (int i=0; i<size; i++){
             if (event.getSource()==app[i]){
                 if (appointment.get(i).isVisited()){
                 appointmentwindow(i,false);}
                 else JOptionPane.showMessageDialog(null, "You haven't visited doctor yet!");}
         
            }
        }
        
        
        @Override
        public void mousePressed (MouseEvent event){
            
        }
        @Override
        public void mouseReleased (MouseEvent event){
            
        }
        @Override
        public void mouseEntered (MouseEvent event){
          
         
        }
        @Override
        public void mouseExited (MouseEvent event){
           
        }
    }
    
    private class Historylistener implements MouseListener{
        @Override
        public void mouseClicked (MouseEvent event){
            if(isdoc==0){
            ArrayList<Appointment> appointment = a.getAllClientAppointments (Integer.parseInt(patientID));
            for (int i=0; i<appdsize; i++){
             if (event.getSource()==apph[i]){
                 if (appointment.get(i).isVisited()){
                 appointmentwindow(i,true);}
                 else JOptionPane.showMessageDialog(null, "You haven't visited doctor yet!");}
         
            }}
            
            if(isdoc!=0){
             ArrayList<Appointment> appointment = a.getAllDoctorAppointments (Integer.parseInt(docID));
            for (int i=0; i<appdsize1; i++){
             if (event.getSource()==apphd[i]){
                 if (appointment.get(i).isVisited()){
                 appointmentwindowd(i,appointment.get(i).getClientId(),true);}
                 else JOptionPane.showMessageDialog(null, "You haven't visited doctor yet!");}
         
            }   
            }
        }
        
        
        @Override
        public void mousePressed (MouseEvent event){
            
        }
        @Override
        public void mouseReleased (MouseEvent event){
            
        }
        @Override
        public void mouseEntered (MouseEvent event){
          
         
        }
        @Override
        public void mouseExited (MouseEvent event){
           
        }
    }
    private class Doctorlist implements MouseListener{
        @Override
        public void mouseClicked (MouseEvent event){
            for (int i=0; i<dsize; i++){
             if (event.getSource()==docl[i]){
                 finalregisterwindow(i);
                 mak.dispose(); 
                 }
            }
        }
        
        
        @Override
        public void mousePressed (MouseEvent event){
            
        }
        @Override
        public void mouseReleased (MouseEvent event){
            
        }
        @Override
        public void mouseEntered (MouseEvent event){
          
         
        }
        @Override
        public void mouseExited (MouseEvent event){
           
        }
    }
    private class AppointmentsD implements MouseListener{
        @Override
        public void mouseClicked (MouseEvent event){
            
            for (int i=0; i<size; i++){
             if (event.getSource()==appd[i]){
                 idp = idarray[i];
                 appointmentwindowd(i,idp, false);
                 
                 
             }
         
            }
        }
        @Override
        public void mousePressed (MouseEvent event){
            
        }
        @Override
        public void mouseReleased (MouseEvent event){
            
        }
        @Override
        public void mouseEntered (MouseEvent event){
          
         
        }
        @Override
        public void mouseExited (MouseEvent event){
           
        }
    }
    private class ScheduleL implements ItemListener{
        @Override 
        public void itemStateChanged (ItemEvent event){
            if(Monday.isSelected()){
                Monday813.setEnabled(true);
                Monday1419.setEnabled(true);
                Mondayis=1;
                
                if(Monday813.isSelected()){
                Monday1419.setEnabled(false);
                Mondayis813=1;
            }
            if(!Monday813.isSelected()){
                Monday1419.setEnabled(true);
                Mondayis813=0;
            }
             if(Monday1419.isSelected()){
                Monday813.setEnabled(false);
                Mondayis1419=1;
            }
            if(!Monday1419.isSelected()){
                Monday813.setEnabled(true);
                Mondayis1419=0;
            }   
            }
            
            if(!Monday.isSelected()){
                Monday813.setEnabled(false);
                Monday1419.setEnabled(false);
                Mondayis=0;
            }
//////////////////////////////////////////////////////////////////            
            
            
            if(Tuesday.isSelected()){
                Tuesday813.setEnabled(true);
                Tuesday1419.setEnabled(true);
                tueis=1;
                 if(Tuesday813.isSelected()){
                Tuesday1419.setEnabled(false);
                tueis813=1;
            }
            if(!Tuesday813.isSelected()){
                Tuesday1419.setEnabled(true);
                tueis813=0;
            }
            if(Tuesday1419.isSelected()){
                Tuesday813.setEnabled(false);
                tueis1419=1;
            }
            if(!Tuesday1419.isSelected()){
                Tuesday813.setEnabled(true);
                tueis1419=0;
            }
            }
            if(!Tuesday.isSelected()){
                Tuesday813.setEnabled(false);
                Tuesday1419.setEnabled(false);
                tueis=0;
            }
 //////////////////////////////////////////////////////////////////////           
            if(Wednesday.isSelected()){
                Wednesday813.setEnabled(true);
                Wednesday1419.setEnabled(true);
                wedis=1;
                if(Wednesday813.isSelected()){
               Wednesday1419.setEnabled(false);
                wedis813=1;
            }
            if(!Wednesday813.isSelected()){
                Wednesday1419.setEnabled(true);
                wedis813=0;
            }
            
            if(Wednesday1419.isSelected()){
               Wednesday813.setEnabled(false);
                wedis1419=1;
            }
            if(!Wednesday1419.isSelected()){
                Wednesday813.setEnabled(true);
                wedis1419=0;
            }
            }
            
            if(!Wednesday.isSelected()){
                Wednesday813.setEnabled(false);
                Wednesday1419.setEnabled(false);
                wedis=0;
            }
 ////////////////////////////////////////////////////////////////////////////           
            if(Thursday.isSelected()){
                Thursday813.setEnabled(true);
                Thursday1419.setEnabled(true);
                thuris=1;
                 if(Thursday813.isSelected()){
                Thursday1419.setEnabled(false);
                thuris813=1;
            }
            if(!Thursday813.isSelected()){
                Thursday1419.setEnabled(true);
                thuris813=0;
            }
             if(Thursday1419.isSelected()){
                Thursday813.setEnabled(false);
                thuris1419=1;
            }
            if(!Thursday1419.isSelected()){
                Thursday813.setEnabled(true);
                thuris1419=0;
            }
            }
            if(!Thursday.isSelected()){
                Thursday813.setEnabled(false);
                Thursday1419.setEnabled(false);
                thuris=0;
            }
 /////////////////////////////////////////////////////////////////////////////           
            if(Friday.isSelected()){
                Friday813.setEnabled(true);
                Friday1419.setEnabled(true);
                friis=1;
                if(Friday813.isSelected()){
               Friday1419.setEnabled(false);
                friis813=1;
            }
            if(!Friday813.isSelected()){
                Friday1419.setEnabled(true);
                friis813=0;
            }
             if(Friday1419.isSelected()){
                Friday813.setEnabled(false);
                friis1419=1;
            }
            if(!Friday1419.isSelected()){
                Friday813.setEnabled(true);
                friis1419=0;
            }
            }
            if(!Friday.isSelected()){
                Friday813.setEnabled(false);
                Friday1419.setEnabled(false);
                friis=0;
            }
            ///////////////////////////////////////////////////////////////////////////////////////////
         
           
            
            
        }
    }
     private class Choosetime implements ItemListener{
        @Override 
        public void itemStateChanged (ItemEvent event){
            
            for (int i=0;i<5;i++){ 
                
            if(selecthm[i].isSelected()){
                time = selecthm[i].getText();

                if(apptime.getDayOfWeekWord().equals("Monday")){
                    day=0;
                }
                if(apptime.getDayOfWeekWord().equals("Tuesday")){
                    day=6;
                }
                if(apptime.getDayOfWeekWord().equals("Wednesday")){
                    day=5;
                }
                if(apptime.getDayOfWeekWord().equals("Thursday")){
                    day=4;
                }
                if(apptime.getDayOfWeekWord().equals("Friday")){
                    day=3;
                }
                
                for(int j=0; j<5; j++){
                   selecthm[j].setEnabled(false);
                   selectht[j].setEnabled(false);
                   selecthw[j].setEnabled(false);
                   selecthth[j].setEnabled(false);
                   selecthf[j].setEnabled(false);                                           
                }
            }
                
          
           
                if(selectht[i].isSelected()){

                time = selectht[i].getText();
                System.out.println(time);
                if(apptime.getDayOfWeekWord().equals("Monday")){
                    day=1;
                }
                if(apptime.getDayOfWeekWord().equals("Tuesday")){
                    day=0;
                }
                if(apptime.getDayOfWeekWord().equals("Wednesday")){
                    day=6;
                }
                if(apptime.getDayOfWeekWord().equals("Thursday")){
                    day=5;
                }
                if(apptime.getDayOfWeekWord().equals("Friday")){
                    day=4;
                }
                 for(int j=0; j<5; j++){
                   selecthm[j].setEnabled(false);
                   selectht[j].setEnabled(false);
                   selecthw[j].setEnabled(false);
                   selecthth[j].setEnabled(false);
                   selecthf[j].setEnabled(false);}
       
            }
          
               
                if(selecthw[i].isSelected()){

                time = selecthw[i].getText();
                 if(apptime.getDayOfWeekWord().equals("Monday")){
                    day=2;
                }
                if(apptime.getDayOfWeekWord().equals("Tuesday")){
                    day=1;
                }
                if(apptime.getDayOfWeekWord().equals("Wednesday")){
                    day=0;
                }
                if(apptime.getDayOfWeekWord().equals("Thursday")){
                    day=6;
                }
                if(apptime.getDayOfWeekWord().equals("Friday")){
                    day=5;
                }
                 for(int j=0; j<5; j++){
                   selecthm[j].setEnabled(false);
                   selectht[j].setEnabled(false);
                   selecthw[j].setEnabled(false);
                   selecthth[j].setEnabled(false);
                   selecthf[j].setEnabled(false);}
                   
                           
                    
                
            } 
            
               
               if(selecthth[i].isSelected()){ 

               time = selecthth[i].getText();
                 if(apptime.getDayOfWeekWord().equals("Monday")){
                    day=3;
                }
                if(apptime.getDayOfWeekWord().equals("Tuesday")){
                    day=2;
                }
                if(apptime.getDayOfWeekWord().equals("Wednesday")){
                    day=1;
                }
                if(apptime.getDayOfWeekWord().equals("Thursday")){
                    day=0;
                }
                if(apptime.getDayOfWeekWord().equals("Friday")){
                    day=6;
                }
                 for(int j=0; j<5; j++){
                   selecthm[j].setEnabled(false);
                   selectht[j].setEnabled(false);
                   selecthw[j].setEnabled(false);
                   selecthth[j].setEnabled(false);
                   selecthf[j].setEnabled(false);}
                   
                           
                    
           }
           
               
               if(selecthf[i].isSelected()){

                time = selecthf[i].getText();
                  if(apptime.getDayOfWeekWord().equals("Monday")){
                    day=4;
                }
                if(apptime.getDayOfWeekWord().equals("Tuesday")){
                    day=3;
                }
                if(apptime.getDayOfWeekWord().equals("Wednesday")){
                    day=2;
                }
                if(apptime.getDayOfWeekWord().equals("Thursday")){
                    day=1;
                }
                if(apptime.getDayOfWeekWord().equals("Friday")){
                    day=0;
                }
                 for(int j=0; j<5; j++){
                   selecthm[j].setEnabled(false);
                   selectht[j].setEnabled(false);
                   selecthw[j].setEnabled(false);
                   selecthth[j].setEnabled(false);
                   selecthf[j].setEnabled(false);}
                   
                           
                    
            }
        }
     
     }}}
   
