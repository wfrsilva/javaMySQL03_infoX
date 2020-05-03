/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import br.com.infox.icones.Icone;
import java.util.HashMap;
import javax.swing.Icon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;  //  https://youtu.be/FvGjkVzdZ3Y 
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class TelaOS extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    //variavel radio button
    private String tipo;
    Icone icone = new Icone();
    Icon printIcon = icone.printIcon();

    /**
     * * Creates new form TelaOS
     */
    public TelaOS() {
        initComponents();
        conexao = ModuloConexao.conector();
    }//TelaOS

    private void pesquisarCliente() {
        String sql = "select idcli as Id, nomecli as Nome, fonecli as Fone from tbclientes where nomecli like ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
        } //try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaOS.pesquisarCliente() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
    }//pesquisarCliente

    private void setarCampos() {
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
    }//setarCampos

    //cadastrar uma OS
    private void emitirOS() {
        String sql = "insert into tbos(tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) values(?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOSSit.getSelectedItem().toString());
            pst.setString(3, txtOSEquip.getText());
            pst.setString(4, txtOSDef.getText());
            pst.setString(5, txtOSServ.getText());
            pst.setString(6, txtOSTec.getText());
            //substitui virgula por ponto
            pst.setString(7, txtOSValor.getText().replace(",", "."));
            pst.setString(8, txtCliId.getText());

            //validar campos obrigatorios
            if ((txtCliId.getText().isEmpty()) || (txtOSEquip.getText().isEmpty()) || (txtOSDef.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            }//if
            else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço: (" + txtOSEquip.getText() + ") EMITIDA com sucesso!", "OS emitida com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCamposClienteDetalhesOS();
                }//if

            }//else

        } //try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaOS.emitirOS() -> ERRO", JOptionPane.ERROR_MESSAGE);

        }//catch
    }//emitirOS

    //pesquisar uma OS
    private void pesquisarOS() {
        String numOS = JOptionPane.showInputDialog("Número da OS: ");
        String sql = "Select * from tbos where os=" + numOS;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                //radio buttons
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    rbtOS.setSelected(true);
                    tipo = "OS";
                }//if 
                else {
                    rbtOrc.setSelected(true);
                    tipo = "Orçamento";
                }//else
                cboOSSit.setSelectedItem(rs.getString(4));
                txtOSEquip.setText(rs.getString(5));
                txtOSDef.setText(rs.getString(6));
                txtOSServ.setText(rs.getString(7));
                txtOSTec.setText(rs.getString(8));
                txtOSValor.setText(rs.getString(9));
                txtCliId.setText(rs.getString(10));

                //evitando problemas adicao x aleracao
                btnOSAdicionar.setEnabled(false);
                txtCliPesquisar.setEnabled(false);
                tblClientes.setVisible(false);

            }//if 
            else {
                JOptionPane.showMessageDialog(null, "OS Não cadastrada", "TelaOS.pesquisarOS Não Cadastrada", JOptionPane.ERROR_MESSAGE);
            }//else

        }//try
        catch (com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "Ordem de Serviço INVÁLIDA!", "TelaOS.pesquisarOS() -> OS Inválida", JOptionPane.ERROR_MESSAGE);
        }//catch
        catch (Exception er) {
            JOptionPane.showMessageDialog(null, er, "TelaOS.pesquisarOS() -> ERRO", JOptionPane.ERROR_MESSAGE);
            System.out.println(er);
        }//catch
    }//pesquisarOS

    //alterar os
    private void alterarOS() {
        String sql = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOSSit.getSelectedItem().toString());
            pst.setString(3, txtOSEquip.getText());
            pst.setString(4, txtOSDef.getText());
            pst.setString(5, txtOSServ.getText());
            pst.setString(6, txtOSTec.getText());
            //substitui virgula por ponto
            pst.setString(7, txtOSValor.getText().replace(",", "."));
            pst.setString(8, txtOS.getText());
            //validar campso obrigatorios
            if ((txtCliId.getText().isEmpty()) || (txtOSEquip.getText().isEmpty()) || (txtOSDef.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos OBRIGATÓRIOS!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            }//if
            else {
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço: (" + txtOSEquip.getText() + ") ALTERADA com Sucesso", "OS alterada com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                    //Habilitar objetos
                    habilitarObjetos();
                }//if
            }//else
        }//try 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaOS.alterarOS - > ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
    }//alterarOS

    //excluir os
    private void excluirOS() {
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja excluir esta Ordem de Serviço ("+ txtOS.getText() +")?", "Excluir OS "+txtOS.getText()+" ?", JOptionPane.YES_NO_OPTION);

        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "delete from tbos where os=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOS.getText());
                int apagado = pst.executeUpdate();

                if (apagado > 0) {
                    JOptionPane.showMessageDialog(null, "Ordem de Serviço: (" + txtOS.getText() + ") EXCLUIDA com sucesso!", "OS excluida com sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparTodosCampos();
                     //Habilitar objetos
                    habilitarObjetos();
                }//if

            }//try 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "TelaOS.excluirOS() -> ERRO", JOptionPane.ERROR_MESSAGE);
            }//catch

        }//if

    }//excluirOS
    
   private void imprimirOS(){
       // Imprimir OS
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a impressão da Ordem de Serviço?", "Impressão OS", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE, icone.printIcon());
        if(confirma == JOptionPane.YES_OPTION){
            //imprimir framework JasperReports
            try {
                //hashmap para filtro
                HashMap filtro = new HashMap();
                filtro.put("os", Integer.parseInt(txtOS.getText()));
                //JasperPrint
                JasperPrint print = JasperFillManager.fillReport("E:\\java\\javaMySQL03_infoX\\reports\\os.jasper",filtro, conexao);
                JasperViewer.viewReport(print, false);                
            } //try
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e, "TelaOS.imprimirOS() -> ERRO", JOptionPane.ERROR_MESSAGE);
                System.out.println("TelaOS.imprimirOS()s -> ERRO\\r"+ e);
            }//catch
        }//if                                  
      
   }//imprimirOS

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        lblOS = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOrc = new javax.swing.JRadioButton();
        rbtOS = new javax.swing.JRadioButton();
        lblOSSit = new javax.swing.JLabel();
        cboOSSit = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        txtCliPesquisar = new javax.swing.JTextField();
        lblIconPesquisar = new javax.swing.JLabel();
        lblCliId = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblOSEquip = new javax.swing.JLabel();
        lblOSDef = new javax.swing.JLabel();
        lblOSServ = new javax.swing.JLabel();
        lblOSTec = new javax.swing.JLabel();
        lblOSValor = new javax.swing.JLabel();
        txtOSEquip = new javax.swing.JTextField();
        txtOSDef = new javax.swing.JTextField();
        txtOSServ = new javax.swing.JTextField();
        txtOSTec = new javax.swing.JTextField();
        txtOSValor = new javax.swing.JTextField();
        btnOSAdicionar = new javax.swing.JButton();
        btnOSPesquisar = new javax.swing.JButton();
        btnOSAlterar = new javax.swing.JButton();
        btnOSExcluir = new javax.swing.JButton();
        btnOSImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        setPreferredSize(new java.awt.Dimension(618, 540));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lblOS.setText("Nº OS");

        lblData.setText("Data");

        txtOS.setEditable(false);
        txtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOSActionPerformed(evt);
            }
        });

        txtData.setEditable(false);
        txtData.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOS);
        rbtOS.setText("Ordem de Serviço");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblOS)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbtOrc))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                        .addComponent(rbtOS)
                        .addGap(31, 31, 31))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblData)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txtData))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOS)
                    .addComponent(lblData))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtOrc)
                    .addComponent(rbtOS))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblOSSit.setText("Situação");

        cboOSSit.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na Bancada", "Entrega OK", "Orçamento REPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        lblIconPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        lblCliId.setText("Id *");

        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Nome", "Fone"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblIconPesquisar)
                        .addGap(24, 24, 24)
                        .addComponent(lblCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCliId))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIconPesquisar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCliId)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lblOSEquip.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSEquip.setText("Equipamento *");

        lblOSDef.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSDef.setText("Defeito *");

        lblOSServ.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSServ.setText("Serviço");

        lblOSTec.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblOSTec.setText("Técnico");

        lblOSValor.setText("Valor Total");

        txtOSEquip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtOSEquipActionPerformed(evt);
            }
        });

        txtOSValor.setText("0");

        btnOSAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnOSAdicionar.setToolTipText("Adicionar OS");
        btnOSAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSAdicionar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSAdicionarActionPerformed(evt);
            }
        });

        btnOSPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/read.png"))); // NOI18N
        btnOSPesquisar.setToolTipText("Pesquisar OS");
        btnOSPesquisar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSPesquisar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSPesquisarActionPerformed(evt);
            }
        });

        btnOSAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnOSAlterar.setToolTipText("Alterar OS");
        btnOSAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSAlterar.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSAlterarActionPerformed(evt);
            }
        });

        btnOSExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnOSExcluir.setToolTipText("Excluir OS");
        btnOSExcluir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSExcluir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSExcluirActionPerformed(evt);
            }
        });

        btnOSImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/print.png"))); // NOI18N
        btnOSImprimir.setToolTipText("Imprimir OS");
        btnOSImprimir.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnOSImprimir.setPreferredSize(new java.awt.Dimension(80, 80));
        btnOSImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOSImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblOSSit)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cboOSSit, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblOSServ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOSDef, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOSEquip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblOSTec, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtOSTec, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(26, 26, 26)
                                .addComponent(lblOSValor)
                                .addGap(18, 18, 18)
                                .addComponent(txtOSValor, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
                            .addComponent(txtOSServ, javax.swing.GroupLayout.DEFAULT_SIZE, 498, Short.MAX_VALUE)
                            .addComponent(txtOSDef)
                            .addComponent(txtOSEquip, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(btnOSAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOSPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOSAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOSExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnOSImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblOSSit)
                            .addComponent(cboOSSit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOSEquip)
                    .addComponent(txtOSEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOSDef)
                    .addComponent(txtOSDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOSServ)
                    .addComponent(txtOSServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblOSTec)
                    .addComponent(lblOSValor)
                    .addComponent(txtOSTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtOSValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 39, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOSPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSAlterar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnOSAdicionar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(63, 63, 63))
        );

        setBounds(0, 0, 618, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // delete-me, inserido acidentalmente
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void txtOSEquipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOSEquipActionPerformed
        // delete-me, inserido acidentalmente
    }//GEN-LAST:event_txtOSEquipActionPerformed

    private void btnOSAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSAdicionarActionPerformed
        // metodo emitirOS()
        emitirOS();
    }//GEN-LAST:event_btnOSAdicionarActionPerformed

    private void txtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtOSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtOSActionPerformed

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // metodo pesquisarCliente
        pesquisarCliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        // metodo setarCampos
        setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
        // texto a variavel tipo qdo selecionado
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        // texto a variavel tipo qdo selecionado
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        //ao abrir o form, marcar radio orcamento
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnOSPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSPesquisarActionPerformed
        // chama  metodo pesquisarOS
        pesquisarOS();
    }//GEN-LAST:event_btnOSPesquisarActionPerformed

    private void btnOSAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSAlterarActionPerformed
        // chama metodo alterarOS
        alterarOS();

    }//GEN-LAST:event_btnOSAlterarActionPerformed

    private void btnOSExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSExcluirActionPerformed
        //chama metodo excluirOS
        excluirOS();
    }//GEN-LAST:event_btnOSExcluirActionPerformed

    private void btnOSImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOSImprimirActionPerformed
        // chama imprimirOS
        imprimirOS();
    }//GEN-LAST:event_btnOSImprimirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOSAdicionar;
    private javax.swing.JButton btnOSAlterar;
    private javax.swing.JButton btnOSExcluir;
    private javax.swing.JButton btnOSImprimir;
    private javax.swing.JButton btnOSPesquisar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOSSit;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliId;
    private javax.swing.JLabel lblData;
    private javax.swing.JLabel lblIconPesquisar;
    private javax.swing.JLabel lblOS;
    private javax.swing.JLabel lblOSDef;
    private javax.swing.JLabel lblOSEquip;
    private javax.swing.JLabel lblOSServ;
    private javax.swing.JLabel lblOSSit;
    private javax.swing.JLabel lblOSTec;
    private javax.swing.JLabel lblOSValor;
    private javax.swing.JRadioButton rbtOS;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliPesquisar;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtOSDef;
    private javax.swing.JTextField txtOSEquip;
    private javax.swing.JTextField txtOSServ;
    private javax.swing.JTextField txtOSTec;
    private javax.swing.JTextField txtOSValor;
    // End of variables declaration//GEN-END:variables

    private void limparTodosCampos() {
        limparCamposClienteDetalhesOS();
        txtOS.setText(null);
        txtData.setText(null);

    }//limparTodosCampos

    private void limparCamposClienteDetalhesOS() {
        txtCliId.setText(null);
        txtOSEquip.setText(null);
        txtOSDef.setText(null);
        txtOSServ.setText(null);
        txtOSTec.setText(null);
        txtOSValor.setText(null);
    }//limparCamposClienteDetalhesOS
    
    private void habilitarObjetos(){
        btnOSAdicionar.setEnabled(true);
        txtCliPesquisar.setEnabled(true);
        tblClientes.setVisible(true);
    }//habilitarObjetos
    
    

}//class TelaOS
