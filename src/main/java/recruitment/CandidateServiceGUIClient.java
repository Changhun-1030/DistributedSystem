/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package recruitment;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import javax.swing.*;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CandidateServiceGUIClient extends javax.swing.JFrame {

    private final ManagedChannel channel;
    private StreamObserver<ChatMessage> chatStream;

    
    public CandidateServiceGUIClient() {
        
        channel = ManagedChannelBuilder.forAddress("localhost", 50051)
        .usePlaintext()
        .build();

        
    Metadata headers = new Metadata();
    headers.put(Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER), "secure-key-2025");


    CandidateEvaluationServiceGrpc.CandidateEvaluationServiceStub stub =
    MetadataUtils.attachHeaders(
        CandidateEvaluationServiceGrpc.newStub(channel),
        headers
    );
    chatStream = stub.realTimeChat(new StreamObserver<ChatMessage>() {
    @Override
    public void onNext(ChatMessage value) {
        jTextArea1.append(value.getSender() + ": " + value.getMessage() + "\n");
    }

    @Override
    public void onError(Throwable t) {
        jTextArea1.append("[Error]: " + t.getMessage() + "\n");
    }

    @Override
    public void onCompleted() {
        jTextArea1.append("Chat ended.\n");
    }
});
      initComponents();
         setTitle("Candidate Service GUI");
        setLocationRelativeTo(null);
        
        
        
        jButton1.addActionListener(e -> submitApplication());
        jButton2.addActionListener(e -> checkStatus());
        jButton3.addActionListener(e -> {
    String text = jTextField7.getText();
    if (!text.isBlank()) {
        ChatMessage msg = ChatMessage.newBuilder()
                .setSender("Client")
                .setMessage(text)
                .setTimestamp(String.valueOf(System.currentTimeMillis()))
                .build();

        chatStream.onNext(msg);
        jTextArea1.append("You: " + text + "\n");
        jTextField7.setText("");
    }
});
        jButton4.addActionListener(e -> recommendJobs());
        jButton5.addActionListener(e -> scheduleInterview());
        jButton6.addActionListener(e -> evaluateCandidate());



       
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextField7 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel1.setText("Candidate ID:");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel3.setText("Resume URL:");

        jLabel4.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel4.setText("Skills:");

        jLabel5.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel5.setText("JOB ID:");

        jButton1.setText("Submit your application");

        jButton2.setText("Check status");

        jLabel6.setFont(new java.awt.Font("맑은 고딕", 0, 14)); // NOI18N
        jLabel6.setText("Chat");

        jButton3.setText("Send");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });

        jButton4.setText("Recommend Jobs");

        jButton5.setText("Schedule Interview");

        jButton6.setText("Evaluate Score");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                            .addComponent(jTextField7, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jButton4)
                            .addGap(18, 18, 18)
                            .addComponent(jButton5)
                            .addGap(18, 18, 18)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
                                    .addComponent(jTextField2)
                                    .addComponent(jTextField3)
                                    .addComponent(jTextField4))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(39, 39, 39)
                                    .addComponent(jButton2))))))
                .addContainerGap(10, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

  
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
   
    }//GEN-LAST:event_jTextField7ActionPerformed

    
    public static void main(String args[]) {
       java.awt.EventQueue.invokeLater(() -> new CandidateServiceGUIClient().setVisible(true));
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CandidateServiceGUIClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CandidateServiceGUIClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CandidateServiceGUIClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CandidateServiceGUIClient.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables

    private void checkStatus() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

    Metadata headers = new Metadata();
    headers.put(Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER), "secure-key-2025");

    CandidateServiceGrpc.CandidateServiceBlockingStub stub =
        MetadataUtils.attachHeaders(
            CandidateServiceGrpc.newBlockingStub(channel),
            headers
        );

    StatusRequest request = StatusRequest.newBuilder()
            .setCandidateId(jTextField1.getText())
            .build();

    StatusResponse response = stub.getCandidateStatus(request);
    JOptionPane.showMessageDialog(this,
            "[Status] " + response.getStatus() +
            "\nInterview date: " + response.getInterviewDate());

    channel.shutdown();
}

    private void submitApplication() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

    Metadata headers = new Metadata();
    headers.put(Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER), "secure-key-2025");

    CandidateServiceGrpc.CandidateServiceBlockingStub stub =
        MetadataUtils.attachHeaders(
            CandidateServiceGrpc.newBlockingStub(channel),
            headers
        );

    ApplicationRequest request;
        request = ApplicationRequest.newBuilder()
                .setCandidateId(jTextField1.getText())
                .setName(jTextField2.getText())
                .setResumeUrl(jTextField3.getText())
                .addSkills(jTextField4.getText())
                .setJobId(jTextField7.getText())
                .build();

    ApplicationResponse response = stub.submitApplication(request);
    JOptionPane.showMessageDialog(this, "[Answer] " + response.getMessage());

    channel.shutdown();
    }
    
    private void recommendJobs() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

    Metadata headers = new Metadata();
    headers.put(Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER), "secure-key-2025");

    CandidateEvaluationServiceGrpc.CandidateEvaluationServiceStub stub =
            MetadataUtils.attachHeaders(
                    CandidateEvaluationServiceGrpc.newStub(channel),
                    headers
            );

    RecommendationRequest request = RecommendationRequest.newBuilder()
            .setCandidateId(jTextField1.getText())
            .addPreferredSkills(jTextField4.getText())  
            .build();

    StringBuilder jobList = new StringBuilder("Recommended Jobs:\n");

    stub.recommendJobs(request, new StreamObserver<RecommendationResponse>() {
        @Override
        public void onNext(RecommendationResponse value) {
            for (Job job : value.getJobsList()) {
                jobList.append("- ")
                        .append(job.getTitle())
                        .append(" at ").append(job.getCompany())
                        .append(" (").append(job.getLocation()).append(")\n");
            }
        }

        @Override
        public void onError(Throwable t) {
            JOptionPane.showMessageDialog(null, "Error receiving jobs: " + t.getMessage());
        }

        @Override
        public void onCompleted() {
            JOptionPane.showMessageDialog(null, jobList.toString());
            channel.shutdown();
        }
    });
}
    private void scheduleInterview() {
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
            .usePlaintext()
            .build();

    Metadata headers = new Metadata();
    headers.put(Metadata.Key.of("api-key", Metadata.ASCII_STRING_MARSHALLER), "secure-key-2025");

    InterviewSchedulerServiceGrpc.InterviewSchedulerServiceBlockingStub stub =
            MetadataUtils.attachHeaders(
                    InterviewSchedulerServiceGrpc.newBlockingStub(channel),
                    headers
            );

   
    List<String> next7Days = new ArrayList<>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    LocalDateTime baseTime = LocalDateTime.now().withHour(10).withMinute(0);  

    for (int i = 0; i < 7; i++) {
        next7Days.add(baseTime.plusDays(i).format(formatter));
    }

    
    String selectedTime = (String) JOptionPane.showInputDialog(
            this,
            "Select your preferred interview time:",
            "Interview Time Selection",
            JOptionPane.QUESTION_MESSAGE,
            null,
            next7Days.toArray(),
            next7Days.get(0)
    );

    if (selectedTime == null) {
        channel.shutdown();
        return;  
    }

    String interviewerId = jTextField1.getText();

    ScheduleRequest request = ScheduleRequest.newBuilder()
            .setCandidateId(jTextField1.getText())
            .setInterviewerId(interviewerId)
            .setPreferredTime(selectedTime)
            .build();

    ScheduleResponse response = stub.scheduleInterview(request);
    JOptionPane.showMessageDialog(this,
            "Interview scheduled with " + interviewerId +
                    " at " + response.getScheduledTime());

    channel.shutdown();
}
    
    private void evaluateCandidate() {
    String[] skills = jTextField4.getText().split("[,\\s]+");
    StringBuilder result = new StringBuilder("Skill-based scores\n-------------------\n");

    if (skills.length == 0 || jTextField4.getText().isBlank()) {
        result.append("No skills entered: 50 point\n");
    } else {
        for (String skill : skills) {
            skill = skill.trim().toLowerCase();
            int score = 50;

            switch (skill) {
                case "java":
                case "c++":
                    score = 90;
                    break;
                case "python":
                    score = 80;
                    break;
                case "mysql":
                    score = 70;
                    break;
                case "html":
                    score = 60;
                    break;
                default:
                    score = 55;
            }

            result.append(skill).append(": ").append(score).append(" point\n");
        }
    }

    JOptionPane.showMessageDialog(this, result.toString(), "Evaluation score", JOptionPane.INFORMATION_MESSAGE);
}

}
