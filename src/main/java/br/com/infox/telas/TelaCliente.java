/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.telas;

import java.sql.*;
import br.com.infox.dal.ModuloConexao;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;  //  https://youtu.be/FvGjkVzdZ3Y

/**
 *
 * @author w7
 */
public class TelaCliente extends javax.swing.JInternalFrame {
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaCliente
     */
    public TelaCliente() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCamposObrigatorios = new javax.swing.JLabel();
        lblNomeCli = new javax.swing.JLabel();
        lblEndCli = new javax.swing.JLabel();
        lblFoneCli = new javax.swing.JLabel();
        lblemailCli = new javax.swing.JLabel();
        txtCliNome = new javax.swing.JTextField();
        txtCliEndereco = new javax.swing.JTextField();
        txtCliFone = new javax.swing.JTextField();
        txtCliEmail = new javax.swing.JTextField();
        btnCliAdicionar = new javax.swing.JButton();
        btnCliAlterar = new javax.swing.JButton();
        btnCliRemover = new javax.swing.JButton();
        txtCliPesquisar = new javax.swing.JTextField();
        lblCliPesquisar = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblClientes = new javax.swing.JTable();
        lblCliId = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Clientes");
        setPreferredSize(new java.awt.Dimension(620, 540));

        lblCamposObrigatorios.setText("* Campos Obrigatorios");

        lblNomeCli.setText("Nome *");

        lblEndCli.setText("Endereço");

        lblFoneCli.setText("Telefone *");

        lblemailCli.setText("e-mail");

        txtCliNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliNomeActionPerformed(evt);
            }
        });

        btnCliAdicionar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/create.png"))); // NOI18N
        btnCliAdicionar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAdicionar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAdicionarActionPerformed(evt);
            }
        });

        btnCliAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/update.png"))); // NOI18N
        btnCliAlterar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCliAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliAlterarActionPerformed(evt);
            }
        });

        btnCliRemover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/delete.png"))); // NOI18N
        btnCliRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCliRemoverActionPerformed(evt);
            }
        });

        txtCliPesquisar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliPesquisarKeyReleased(evt);
            }
        });

        lblCliPesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/infox/icones/pesquisar.png"))); // NOI18N

        tblClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClientesMouseClicked(evt);
            }
        });
        tblClientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tblClientesKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblClientes);

        lblCliId.setText("Id Cliente");

        txtCliId.setEditable(false);
        txtCliId.setEnabled(false);
        txtCliId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCliIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnCliAdicionar)
                        .addGap(88, 88, 88)
                        .addComponent(btnCliAlterar)
                        .addGap(73, 73, 73)
                        .addComponent(btnCliRemover))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 298, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblCliPesquisar)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNomeCli)
                        .addGap(26, 26, 26)
                        .addComponent(txtCliNome))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEndCli)
                        .addGap(18, 18, 18)
                        .addComponent(txtCliEndereco))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(lblCamposObrigatorios)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(lblFoneCli)
                                            .addComponent(lblemailCli))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lblCliId)
                                .addGap(18, 18, 18)
                                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 3, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCliPesquisar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCamposObrigatorios)
                    .addComponent(txtCliPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCliId)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNomeCli)
                    .addComponent(txtCliNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEndCli)
                    .addComponent(txtCliEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFoneCli)
                    .addComponent(txtCliFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblemailCli)
                    .addComponent(txtCliEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCliAdicionar, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnCliAlterar)
                    .addComponent(btnCliRemover))
                .addGap(45, 45, 45))
        );

        getAccessibleContext().setAccessibleName("Clientes");
        getAccessibleContext().setAccessibleDescription("Tela de Clientes");

        setBounds(0, 0, 620, 540);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliNomeActionPerformed

    private void btnCliAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAlterarActionPerformed
        // chama metodo alterar cliente
        alterar();
    }//GEN-LAST:event_btnCliAlterarActionPerformed

    private void btnCliAdicionarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliAdicionarActionPerformed
        // Adiciona cliente
        adicionar();
    }//GEN-LAST:event_btnCliAdicionarActionPerformed

    private void tblClientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblClientesKeyReleased
        // era para ser o metodo txtCliPesquisarKeyReleased, criei esse aqui equivocadamente
       // pesquisarCliente();
    }//GEN-LAST:event_tblClientesKeyReleased

    private void txtCliPesquisarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliPesquisarKeyReleased
        // Chamar o metodo pesquisarCliente
        pesquisarCliente();
    }//GEN-LAST:event_txtCliPesquisarKeyReleased

    private void tblClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClientesMouseClicked
        //evento para setar os campos da tabela clicando com o mouse
        setarCampos();
    }//GEN-LAST:event_tblClientesMouseClicked

    private void txtCliIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCliIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCliIdActionPerformed

    private void btnCliRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCliRemoverActionPerformed
        // remover cliente
        remover();
    }//GEN-LAST:event_btnCliRemoverActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCliAdicionar;
    private javax.swing.JButton btnCliAlterar;
    private javax.swing.JButton btnCliRemover;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCamposObrigatorios;
    private javax.swing.JLabel lblCliId;
    private javax.swing.JLabel lblCliPesquisar;
    private javax.swing.JLabel lblEndCli;
    private javax.swing.JLabel lblFoneCli;
    private javax.swing.JLabel lblNomeCli;
    private javax.swing.JLabel lblemailCli;
    private javax.swing.JTable tblClientes;
    private javax.swing.JTextField txtCliEmail;
    private javax.swing.JTextField txtCliEndereco;
    private javax.swing.JTextField txtCliFone;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtCliNome;
    private javax.swing.JTextField txtCliPesquisar;
    // End of variables declaration//GEN-END:variables
    Icon trashIcon = createImageIcon("/br/com/infox/icones/trash-icon.png", "Você deletou algo!");
    Icon databaseIcon = createImageIcon("/br/com/infox/icones/database-icon.png", "Você Criou algo!");
    Icon dataEditIcon = createImageIcon("/br/com/infox/icones/data-edit-icon.png", "Você alterou algo!");

private void adicionar(){
        String sql = "insert into  tbclientes( nomecli, endcli, fonecli, emailcli)  values(?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());
            //atualiza usuaros com dados do form
            if ((txtCliNome.getText().isEmpty())||(txtCliFone.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            } //if
            else {
                //confirmar insercao tabela
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente: (" + txtCliNome.getText() + ") foi adicionado com sucesso!", "Cliente adicionado com sucesso", JOptionPane.INFORMATION_MESSAGE,databaseIcon);
                    limparTodosCampos();
                }//if
            }//else
            
        }//try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaCliente.adicionar() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch
    }//adicionar

    //pesquisar clientes com filtro
    private void pesquisarCliente(){
        String sql = "Select * from tbclientes where nomecli like ?";
        System.out.println("br.com.infox.telas.TelaCliente.pesquisarCliente()");
        System.out.println("sql: " + sql);
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliPesquisar.getText() + "%");
            rs = pst.executeQuery();
            tblClientes.setModel(DbUtils.resultSetToTableModel(rs));
            
        } //thry
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaCliente.pesquisarCliente() -> ERRO", JOptionPane.ERROR_MESSAGE);
       }//catch
        
    }//pesquisarCliente
    
    public void setarCampos(){
        int setar = tblClientes.getSelectedRow();
        txtCliId.setText(tblClientes.getModel().getValueAt(setar, 0).toString());
        txtCliNome.setText(tblClientes.getModel().getValueAt(setar, 1).toString());
        txtCliEndereco.setText(tblClientes.getModel().getValueAt(setar, 2).toString());
        txtCliFone.setText(tblClientes.getModel().getValueAt(setar, 3).toString());
        txtCliEmail.setText(tblClientes.getModel().getValueAt(setar, 4).toString());
        //desabilita o botao adicionar
        btnCliAdicionar.setEnabled(false);
        
    } //setarCampos
    
    private void alterar() {
        String sql = "update tbclientes set nomecli=?, endcli=?, fonecli=?, emailcli=? where idcli=?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCliNome.getText());
            pst.setString(2, txtCliEndereco.getText());
            pst.setString(3, txtCliFone.getText());
            pst.setString(4, txtCliEmail.getText());
            pst.setString(5, txtCliId.getText());

            if ( (txtCliNome.getText().isEmpty()) || (txtCliFone.getText().isEmpty()) ) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obrigatórios!", "Preencha os campos obrigatórios", JOptionPane.WARNING_MESSAGE);
            } //if
            else {
                //confirmar alteracao tabela
                int alterado = pst.executeUpdate();
                if (alterado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados do Cliente: (" + txtCliNome.getText() + ") foram ALTERADOS com sucesso!", "Dados do cliente alterados com sucesso", JOptionPane.INFORMATION_MESSAGE, dataEditIcon);
                    limparTodosCampos();
                    pesquisarCliente();
                    btnCliAdicionar.setEnabled(true);
                }//if
            }//else
        }//try 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "TelaCliente.alterar() -> ERRO", JOptionPane.ERROR_MESSAGE);
        }//catch

    }//alterar
    
    private void remover(){
        //confirmar remocao
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover esse Cliente?","Atenção - Deseja Remover?", JOptionPane.YES_NO_OPTION);
        
        if (confirma==JOptionPane.YES_OPTION) {
            String sql = "delete from tbclientes where idcli=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtCliId.getText());
                int apagado = pst.executeUpdate();
                if (apagado>0){
                    
                    JOptionPane.showMessageDialog(null, "Cliente: (" + txtCliNome.getText() + ") foi REMOVIDO com sucesso!", "Cliente REMOVIDO com sucesso", JOptionPane.INFORMATION_MESSAGE, trashIcon);
                    limparTodosCampos();
                }//if
            }//try 
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e,"TelaCliente.remover() -> ERRO", JOptionPane.ERROR_MESSAGE);
            }//catch
            
        }//if 
        else {
            System.out.println("TelaCliente.remover() - desistiu de deletar o cliente.");
        }//else
        
    }//remover

    private void limparTodosCampos(){
        txtCliNome.setText(null);
        txtCliEndereco.setText(null);
        txtCliFone.setText(null);
        txtCliEmail.setText(null);
    }//limparCamposExcetoID
    
    protected static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imgURL = TelaCliente.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("ImageIcon : Couldn't find file: " + path);
            return null;
        }
    }//ImageIcon

}//class
