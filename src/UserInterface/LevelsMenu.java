package UserInterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import GameManagement.MenuManager;

public class LevelsMenu extends Menu
{
	// VARIABLES
	private int levelsCount;
	private int unlockedLevels;          
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JButton jButton7;
    private JButton jButton8;
    private JButton jButton9;
    private JButton jButton10;
    private JButton jButton11;
	// END OF VARIABLES
	
	// CONSTRUCTORS
	public LevelsMenu( int count, int unlocked )
	{
		super();
		levelsCount = count;
		unlockedLevels = unlocked;
		
		initComponents();
	}
	// END OF CONSTRUCTORS
	
	// MUTATOR - ACCESSOR METHODS
	public int getUnlockedLevels( String selectedEpisode )
	{
		return unlockedLevels;
	}
	// END OF MUTATOR - ACCESSOR METHODS
	
	// OTHER METHODS
	protected void initComponents()
	{
		jButton1 = new JButton();
        jButton2 = new JButton();
        jButton3 = new JButton();
        jButton4 = new JButton();
        jButton5 = new JButton();
        jButton6 = new JButton();
        jButton7 = new JButton();
        jButton8 = new JButton();
        jButton9 = new JButton();
        jButton10 = new JButton();
        jButton11 = new JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level1.png"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setFocusPainted(false);
        jButton1.addActionListener( this );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level2.png"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setFocusPainted(false);
        jButton2.addActionListener( this );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level3.png"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setContentAreaFilled(false);
        jButton3.setFocusPainted(false);
        jButton3.addActionListener( this );

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level4.png"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setContentAreaFilled(false);
        jButton4.setFocusPainted(false);
        jButton4.addActionListener( this );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level5.png"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setContentAreaFilled(false);
        jButton5.setFocusPainted(false);
        jButton5.addActionListener( this );

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level6.png"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setContentAreaFilled(false);
        jButton6.setFocusPainted(false);
        jButton6.addActionListener( this );

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level7.png"))); // NOI18N
        jButton7.setBorderPainted(false);
        jButton7.setContentAreaFilled(false);
        jButton7.setFocusPainted(false);
        jButton7.addActionListener( this );

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level8.png"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setContentAreaFilled(false);
        jButton8.setFocusPainted(false);
        jButton8.addActionListener( this );

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level9.png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setContentAreaFilled(false);
        jButton9.setFocusPainted(false);
        jButton9.addActionListener( this );

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/level10.png"))); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setContentAreaFilled(false);
        jButton10.setFocusPainted(false);
        jButton10.addActionListener( this );

        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/BackButton.png"))); // NOI18N
        jButton11.setBorderPainted(false);
        jButton11.setContentAreaFilled(false);
        jButton11.setFocusPainted(false);
        jButton11.setIconTextGap(0);
        jButton11.setMargin(new java.awt.Insets(0, 0, 0, 0));
        jButton11.setMaximumSize(new java.awt.Dimension(192, 71));
        jButton11.setMinimumSize(new java.awt.Dimension(192, 71));
        jButton11.setPreferredSize(new java.awt.Dimension(192, 71));
        jButton11.addActionListener( this );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton5))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jButton6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton10)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(131, 131, 131)
                        .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );
    }
	public void actionPerformed( ActionEvent e )
	{
        if( e.getSource() == jButton1 )
        {
        	System.out.println( "1" );
        }
        else if( e.getSource() == jButton2 )
        {
        	System.out.println( "2" );
        }
        else if( e.getSource() == jButton3 )
        {
        	System.out.println( "3" );
        }
        else if( e.getSource() == jButton4 )
        {
        	System.out.println( "4" );
        }
        else if( e.getSource() == jButton5 )
        {
        	System.out.println( "5" );
        }
        else if( e.getSource() == jButton6 )
        {
        	System.out.println( "6" );
        }
        else if( e.getSource() == jButton7 )
        {
        	System.out.println( "7" );
        }
        else if( e.getSource() == jButton8 )
        {
        	System.out.println( "8" );
        }
        else if( e.getSource() == jButton9 )
        {
        	System.out.println( "9" );
        }
        else if( e.getSource() == jButton10 )
        {
        	System.out.println( "10" );
        }
        else if( e.getSource() == jButton11 )
    		MenuManager.instance.changeMenu( (Menu) new MainMenu() );
    } 
	// END OF OTHER METHODS	
}