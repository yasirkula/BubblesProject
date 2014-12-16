package UserInterface;

import java.awt.event.ActionEvent;

import GameManagement.MenuManager;

public class CreditsMenu extends Menu
{
	// VARIABLES
	private String creditsText;
	
	private javax.swing.JButton backButton;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTextArea textArea;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public CreditsMenu()
	{
		super();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public void setCreditsText( String c )
	{
		creditsText = c;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents() 
	{
        scrollPane = new javax.swing.JScrollPane();
        textArea = new javax.swing.JTextArea();
        backButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        textArea.setColumns(20);
        textArea.setRows(5);
        textArea.setText("Hello world!");
        scrollPane.setViewportView(textArea);

        backButton.setForeground(new java.awt.Color(255, 255, 255));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackButton.png"))); // NOI18N
        backButton.setBorderPainted(false);
        backButton.setContentAreaFilled(false);
        backButton.setFocusPainted(false);
        backButton.setIconTextGap(0);
        backButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        backButton.setMaximumSize(new java.awt.Dimension(192, 71));
        backButton.setMinimumSize(new java.awt.Dimension(192, 71));
        backButton.setPreferredSize(new java.awt.Dimension(192, 71));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(103, 103, 103))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 28, Short.MAX_VALUE))
        );
    }                      

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                         
        MenuManager.instance.changeMenu( (Menu) new MainMenu() );
    }  
    
    public void actionPerformed( ActionEvent e ) {}
    // END OF OTHER METHODS                            
}