
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * Esta clase es para crear cada cliente y proporciona la interfaz gráfica del chat
 *
 * @author Kendall Marín Muñoz
 */
public class Micliente extends javax.swing.JFrame {
    String iD, clientID = "";
    DataInputStream din;
    DataOutputStream dout;
    DefaultListModel dlm;

    /**
     * Crea un nuevo cliente
     */
    public Micliente() {
        initComponents();
    }

    /**
     * Este método asigna el usuario y el socket al cliente
     *
     * @param i el nombre del usuario en el cliente
     * @param s el socket que utiliza el cliente
     */
     Micliente(String i, Socket s) {
        iD = i;
        try {
            initComponents();
            dlm = new DefaultListModel();
            UL.setModel(dlm);
            idlabel.setText(i);
            din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            new Read().start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Esta clase es para enviar los mensajes
     */
    class Read extends Thread {
        /**
         * Este método envía los mensajes y detecta si se solicitó un monto,
         * respondiendo automáticamente el resultado del cálculo del monto
         */
        public void run() {
            while (true) {
                try {
                    String m = din.readUTF();
                    String monto = m;
                    List<String> calc = Arrays.asList(monto.split(":"));
                    if (calc.get(0).equals("El monto calculado es de")){
                        String c = "El monto calculado es de: " + calc.get(1), cc = c;
                        String CI = clientID;
                        if (!clientID.isEmpty()) {
                            c = "#4344554@@@@@67667@@" + CI + ":" + cc;
                            dout.writeUTF(c);
                            msgText.setText("");
                            msgBox.append("< Tu para " + CI + " > " + cc + "\n");
                        } else {
                            dout.writeUTF(c);
                            msgText.setText("");
                            msgBox.append("< Tu para Todos >" + cc + "\n");
                        }
                    }
                    else if (m.contains(":;.,/=")) {
                        m = m.substring(6);
                        dlm.clear();
                        StringTokenizer st = new StringTokenizer(m, ",");
                        while (st.hasMoreTokens()) {
                            String u = st.nextToken();
                            if (!iD.equals(u)) {
                                dlm.addElement(u);
                            }
                        }
                    } else {
                        msgBox.append("" + m + "\n");
                    }
                } catch (Exception ex) {
                    break;
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idlabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgBox = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        todos = new javax.swing.JButton();
        enviar = new javax.swing.JButton();
        msgText = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        UL = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setAlignmentX(0.0F);
        jPanel1.setAlignmentY(0.0F);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Bienvenido:");

        idlabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        idlabel.setText(".............................");

        msgBox.setColumns(20);
        msgBox.setRows(5);
        jScrollPane1.setViewportView(msgBox);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        todos.setBackground(new java.awt.Color(255, 204, 204));
        todos.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        todos.setText("Todos");
        todos.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        todos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                todos(evt);
            }
        });

        enviar.setFont(new java.awt.Font("Verdana", 0, 14)); // NOI18N
        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviar(evt);
            }
        });

        UL.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                ULValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(UL);

        jLabel2.setText("Si desea calcular un monto, ingréselo con el siguiente formato:");

        jLabel3.setText("monto,valor,impuesto,peso");

        jLabel4.setText("Reemplazando \"valor\",  \"impuesto\" y  \"peso\" con sus respectivos valores ");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Debe enviar el mensaje a TODOS, no olvide las comas y no coloque espacios.");

        jLabel6.setForeground(new java.awt.Color(255, 51, 51));
        jLabel6.setText("ENTEROS.");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 6, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(msgText))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(enviar, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(idlabel)
                    .addComponent(todos, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator1)
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(msgText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(enviar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Este método implementa una lista donde se puede seleccionar un único
     * cliente al cual enviar el mensaje
     *
     * @param evt evento consecuente al seleccionar una opción de la lista
     */
    private void ULValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_ULValueChanged
        clientID = (String) UL.getSelectedValue();
    }//GEN-LAST:event_ULValueChanged

    /**
     * Este método implementa un botón que se encarga de tomar el texto ingresado
     * y enviarlo al servidor como un paquete
     *
     * @param evt evento consecuente al presionar el botón enviar
     */
    private void enviar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviar
        try {
            String m = msgText.getText(), mm = m;
            String CI = clientID;

            if (!clientID.isEmpty()) {
                m = "#4344554@@@@@67667@@" + CI + ":" + mm;
                dout.writeUTF(m);
                msgText.setText("");
                msgBox.append("< Tu para " + CI + " > " + mm + "\n");
            } else {
                dout.writeUTF(m);
                msgText.setText("");
                msgBox.append("< Tu para Todos >" + mm + "\n");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "El usuario ya no está.");
        }
    }//GEN-LAST:event_enviar

    /**
     * Este método implementa un botón con el cual seleccionar a todos los
     * clientes para enviar los mensajes de forma general
     *
     * @param evt evento consecuente al pulsar el botón Todos
     */
    private void todos(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_todos
        clientID = "";
    }//GEN-LAST:event_todos

    /**
     * Este método se encarga de comunicar al servidor si un cliente ha salido
     *
     * @param evt evento consecuente al cerrar la ventana del cliente
     */
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
            String i = "mkoihgteazdcvgyhujb096785542AXTY";
            try {
                dout.writeUTF(i);
                this.dispose();

            } catch (IOException ex) {
                Logger.getLogger(Micliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }// Evento al cerrar la ventana

    /**
     * @param args los argumentos de la línea de comando
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> UL;
    private javax.swing.JButton enviar;
    private javax.swing.JLabel idlabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextArea msgBox;
    private javax.swing.JTextField msgText;
    private javax.swing.JButton todos;
    // End of variables declaration//GEN-END:variables
}
