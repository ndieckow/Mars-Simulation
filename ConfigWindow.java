package s4.informatik.mars;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConfigWindow {
	
	private JFrame frame;
	private JSpinner miningModSpinner;
	private JSpinner smeltingModSpinner;
	private JSpinner efficiencySpinner;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConfigWindow window = new ConfigWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public ConfigWindow() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 620, 397);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {360, 0, 0};
		gridBagLayout.rowHeights = new int[]{294, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JPanel leftPanel = new JPanel();
		leftPanel.setBorder(new EmptyBorder(10, 10, 0, 0));
		GridBagConstraints gbc_leftPanel = new GridBagConstraints();
		gbc_leftPanel.insets = new Insets(0, 0, 5, 5);
		gbc_leftPanel.fill = GridBagConstraints.BOTH;
		gbc_leftPanel.gridx = 0;
		gbc_leftPanel.gridy = 0;
		frame.getContentPane().add(leftPanel, gbc_leftPanel);
		leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel resourcePanel = new JPanel();
		leftPanel.add(resourcePanel);
		GridBagLayout gbl_resourcePanel = new GridBagLayout();
		gbl_resourcePanel.columnWidths = new int[] {112, 112, 56, 56, 0};
		gbl_resourcePanel.rowHeights = new int[] {40, 40, 30, 40, 0};
		gbl_resourcePanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_resourcePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		resourcePanel.setLayout(gbl_resourcePanel);
		
		JLabel lblRessourcen = new JLabel("Ressourcen");
		lblRessourcen.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRessourcen.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblRessourcen = new GridBagConstraints();
		gbc_lblRessourcen.gridwidth = 4;
		gbc_lblRessourcen.fill = GridBagConstraints.BOTH;
		gbc_lblRessourcen.insets = new Insets(0, 0, 5, 5);
		gbc_lblRessourcen.gridx = 0;
		gbc_lblRessourcen.gridy = 0;
		resourcePanel.add(lblRessourcen, gbc_lblRessourcen);
		
		JSlider energySmeltingSlider = new JSlider();
		JSlider energyMiningSlider = new JSlider();
		energyMiningSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				energySmeltingSlider.setValue(100 - energyMiningSlider.getValue());
			}
		});
		energyMiningSlider.setPaintTicks(true);
		GridBagConstraints gbc_energyMiningSlider = new GridBagConstraints();
		gbc_energyMiningSlider.fill = GridBagConstraints.BOTH;
		gbc_energyMiningSlider.insets = new Insets(0, 0, 5, 5);
		gbc_energyMiningSlider.gridx = 0;
		gbc_energyMiningSlider.gridy = 1;
		resourcePanel.add(energyMiningSlider, gbc_energyMiningSlider);
		
		JSlider populationSmeltingSlider = new JSlider();
		JSlider populationMiningSlider = new JSlider();
		populationMiningSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populationSmeltingSlider.setValue(100 - populationMiningSlider.getValue());
			}
		});
		populationMiningSlider.setPaintTicks(true);
		GridBagConstraints gbc_populationMiningSlider = new GridBagConstraints();
		gbc_populationMiningSlider.fill = GridBagConstraints.BOTH;
		gbc_populationMiningSlider.insets = new Insets(0, 0, 5, 5);
		gbc_populationMiningSlider.gridx = 1;
		gbc_populationMiningSlider.gridy = 1;
		resourcePanel.add(populationMiningSlider, gbc_populationMiningSlider);
		
		miningModSpinner = new JSpinner();
		miningModSpinner.setModel(new SpinnerNumberModel(new Float(0.5f), new Float(0), new Float(1), new Float(0.1f)));
		GridBagConstraints gbc_miningModSpinner = new GridBagConstraints();
		gbc_miningModSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_miningModSpinner.anchor = GridBagConstraints.WEST;
		gbc_miningModSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_miningModSpinner.gridx = 2;
		gbc_miningModSpinner.gridy = 1;
		resourcePanel.add(miningModSpinner, gbc_miningModSpinner);
		
		JLabel lblEnergie = new JLabel("Energie");
		lblEnergie.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblEnergie = new GridBagConstraints();
		gbc_lblEnergie.fill = GridBagConstraints.BOTH;
		gbc_lblEnergie.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnergie.gridx = 0;
		gbc_lblEnergie.gridy = 2;
		resourcePanel.add(lblEnergie, gbc_lblEnergie);
		
		JLabel lblBevlkerung = new JLabel("Bev\u00F6lkerung");
		lblBevlkerung.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblBevlkerung = new GridBagConstraints();
		gbc_lblBevlkerung.fill = GridBagConstraints.BOTH;
		gbc_lblBevlkerung.insets = new Insets(0, 0, 5, 5);
		gbc_lblBevlkerung.gridx = 1;
		gbc_lblBevlkerung.gridy = 2;
		resourcePanel.add(lblBevlkerung, gbc_lblBevlkerung);
		
		efficiencySpinner = new JSpinner();
		efficiencySpinner.setModel(new SpinnerNumberModel(new Float(0.9f), new Float(0), new Float(1), new Float(0.1f)));
		efficiencySpinner.setToolTipText("");
		GridBagConstraints gbc_efficiencySpinner = new GridBagConstraints();
		gbc_efficiencySpinner.insets = new Insets(0, 0, 5, 0);
		gbc_efficiencySpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_efficiencySpinner.gridx = 3;
		gbc_efficiencySpinner.gridy = 2;
		resourcePanel.add(efficiencySpinner, gbc_efficiencySpinner);
		
		energySmeltingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				energyMiningSlider.setValue(100 - energySmeltingSlider.getValue());
			}
		});
		energySmeltingSlider.setPaintTicks(true);
		GridBagConstraints gbc_energySmeltingSlider = new GridBagConstraints();
		gbc_energySmeltingSlider.fill = GridBagConstraints.BOTH;
		gbc_energySmeltingSlider.insets = new Insets(0, 0, 0, 5);
		gbc_energySmeltingSlider.gridx = 0;
		gbc_energySmeltingSlider.gridy = 3;
		resourcePanel.add(energySmeltingSlider, gbc_energySmeltingSlider);
		
		populationSmeltingSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				populationMiningSlider.setValue(100 - populationSmeltingSlider.getValue());
			}
		});
		populationSmeltingSlider.setPaintTicks(true);
		GridBagConstraints gbc_populationSmeltingSlider = new GridBagConstraints();
		gbc_populationSmeltingSlider.fill = GridBagConstraints.BOTH;
		gbc_populationSmeltingSlider.insets = new Insets(0, 0, 0, 5);
		gbc_populationSmeltingSlider.gridx = 1;
		gbc_populationSmeltingSlider.gridy = 3;
		resourcePanel.add(populationSmeltingSlider, gbc_populationSmeltingSlider);
		
		smeltingModSpinner = new JSpinner();
		smeltingModSpinner.setModel(new SpinnerNumberModel(new Float(0.2f), new Float(0), new Float(1), new Float(0.1f)));
		GridBagConstraints gbc_smeltingModSpinner = new GridBagConstraints();
		gbc_smeltingModSpinner.anchor = GridBagConstraints.WEST;
		gbc_smeltingModSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_smeltingModSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_smeltingModSpinner.gridx = 2;
		gbc_smeltingModSpinner.gridy = 3;
		resourcePanel.add(smeltingModSpinner, gbc_smeltingModSpinner);
		
		JPanel energyPanel = new JPanel();
		leftPanel.add(energyPanel);
		GridBagLayout gbl_energyPanel = new GridBagLayout();
		gbl_energyPanel.columnWidths = new int[] {100, 50, 35, 100, 50, 0};
		gbl_energyPanel.rowHeights = new int[] {40, 0, 30, 0, 0};
		gbl_energyPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_energyPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		energyPanel.setLayout(gbl_energyPanel);
		
		JLabel lblEnergie_1 = new JLabel("Energie");
		lblEnergie_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblEnergie_1 = new GridBagConstraints();
		gbc_lblEnergie_1.gridwidth = 5;
		gbc_lblEnergie_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnergie_1.gridx = 0;
		gbc_lblEnergie_1.gridy = 0;
		energyPanel.add(lblEnergie_1, gbc_lblEnergie_1);
		
		JLabel lblGeneratoren = new JLabel("Generatoren");
		lblGeneratoren.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGeneratoren = new GridBagConstraints();
		gbc_lblGeneratoren.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGeneratoren.insets = new Insets(0, 0, 5, 5);
		gbc_lblGeneratoren.gridx = 0;
		gbc_lblGeneratoren.gridy = 1;
		energyPanel.add(lblGeneratoren, gbc_lblGeneratoren);
		
		JSpinner generatorSpinner = new JSpinner();
		generatorSpinner.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_generatorSpinner = new GridBagConstraints();
		gbc_generatorSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_generatorSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_generatorSpinner.gridx = 1;
		gbc_generatorSpinner.gridy = 1;
		energyPanel.add(generatorSpinner, gbc_generatorSpinner);
		
		JLabel lblEnergieProPerson = new JLabel("Energie pro Person");
		lblEnergieProPerson.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnergieProPerson = new GridBagConstraints();
		gbc_lblEnergieProPerson.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnergieProPerson.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnergieProPerson.gridx = 3;
		gbc_lblEnergieProPerson.gridy = 1;
		energyPanel.add(lblEnergieProPerson, gbc_lblEnergieProPerson);
		
		JSpinner eppSpinner = new JSpinner();
		eppSpinner.setModel(new SpinnerNumberModel(new Float(1), new Float(0), null, new Float(1)));
		GridBagConstraints gbc_eppSpinner = new GridBagConstraints();
		gbc_eppSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_eppSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_eppSpinner.gridx = 4;
		gbc_eppSpinner.gridy = 1;
		energyPanel.add(eppSpinner, gbc_eppSpinner);
		
		JLabel lblGeneratorenwachstum = new JLabel("Gen.-Zuwachs");
		lblGeneratorenwachstum.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGeneratorenwachstum = new GridBagConstraints();
		gbc_lblGeneratorenwachstum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblGeneratorenwachstum.insets = new Insets(0, 0, 0, 5);
		gbc_lblGeneratorenwachstum.gridx = 0;
		gbc_lblGeneratorenwachstum.gridy = 3;
		energyPanel.add(lblGeneratorenwachstum, gbc_lblGeneratorenwachstum);
		
		JSpinner generatorGrowthSpinner = new JSpinner();
		generatorGrowthSpinner.setModel(new SpinnerNumberModel(new Float(0.2f), new Float(0), null, new Float(0.1f)));
		GridBagConstraints gbc_generatorGrowthSpinner = new GridBagConstraints();
		gbc_generatorGrowthSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_generatorGrowthSpinner.insets = new Insets(0, 0, 0, 5);
		gbc_generatorGrowthSpinner.gridx = 1;
		gbc_generatorGrowthSpinner.gridy = 3;
		energyPanel.add(generatorGrowthSpinner, gbc_generatorGrowthSpinner);
		
		JLabel lblEnergieProGenerator = new JLabel("Energie pro Gen.");
		lblEnergieProGenerator.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblEnergieProGenerator = new GridBagConstraints();
		gbc_lblEnergieProGenerator.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEnergieProGenerator.insets = new Insets(0, 0, 0, 5);
		gbc_lblEnergieProGenerator.gridx = 3;
		gbc_lblEnergieProGenerator.gridy = 3;
		energyPanel.add(lblEnergieProGenerator, gbc_lblEnergieProGenerator);
		
		JSpinner epgSpinner = new JSpinner();
		epgSpinner.setModel(new SpinnerNumberModel(new Float(2), new Float(0.1f), null, new Float(1)));
		GridBagConstraints gbc_epgSpinner = new GridBagConstraints();
		gbc_epgSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_epgSpinner.gridx = 4;
		gbc_epgSpinner.gridy = 3;
		energyPanel.add(epgSpinner, gbc_epgSpinner);
		
		JPanel colonyPanel = new JPanel();
		colonyPanel.setBorder(new EmptyBorder(10, 0, 0, 10));
		GridBagConstraints gbc_colonyPanel = new GridBagConstraints();
		gbc_colonyPanel.insets = new Insets(0, 0, 5, 0);
		gbc_colonyPanel.fill = GridBagConstraints.VERTICAL;
		gbc_colonyPanel.gridx = 1;
		gbc_colonyPanel.gridy = 0;
		frame.getContentPane().add(colonyPanel, gbc_colonyPanel);
		GridBagLayout gbl_colonyPanel = new GridBagLayout();
		gbl_colonyPanel.columnWidths = new int[] {130, 50, 0};
		gbl_colonyPanel.rowHeights = new int[] {40, 0, 30, 0, 30, 30, 0, 30, 0, 30, 0, 0};
		gbl_colonyPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_colonyPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		colonyPanel.setLayout(gbl_colonyPanel);
		
		JLabel lblKolonie = new JLabel("Kolonie");
		lblKolonie.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblKolonie = new GridBagConstraints();
		gbc_lblKolonie.gridwidth = 2;
		gbc_lblKolonie.insets = new Insets(0, 0, 5, 0);
		gbc_lblKolonie.gridx = 0;
		gbc_lblKolonie.gridy = 0;
		colonyPanel.add(lblKolonie, gbc_lblKolonie);
		
		JLabel lblBevlkerung_1 = new JLabel("Bev\u00F6lkerung");
		lblBevlkerung_1.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblBevlkerung_1 = new GridBagConstraints();
		gbc_lblBevlkerung_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBevlkerung_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblBevlkerung_1.gridx = 0;
		gbc_lblBevlkerung_1.gridy = 2;
		colonyPanel.add(lblBevlkerung_1, gbc_lblBevlkerung_1);
		
		JSpinner populationSpinner = new JSpinner();
		populationSpinner.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(10)));
		GridBagConstraints gbc_populationSpinner = new GridBagConstraints();
		gbc_populationSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_populationSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_populationSpinner.gridx = 1;
		gbc_populationSpinner.gridy = 2;
		colonyPanel.add(populationSpinner, gbc_populationSpinner);
		
		JLabel lblBevlkerungswachstum = new JLabel("Bev\u00F6lkerungswachstum");
		lblBevlkerungswachstum.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblBevlkerungswachstum = new GridBagConstraints();
		gbc_lblBevlkerungswachstum.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblBevlkerungswachstum.insets = new Insets(0, 0, 5, 5);
		gbc_lblBevlkerungswachstum.gridx = 0;
		gbc_lblBevlkerungswachstum.gridy = 3;
		colonyPanel.add(lblBevlkerungswachstum, gbc_lblBevlkerungswachstum);
		
		JSpinner popGrowthSpinner = new JSpinner();
		popGrowthSpinner.setModel(new SpinnerNumberModel(new Float(0.2f), new Float(0), null, new Float(0.1f)));
		GridBagConstraints gbc_popGrowthSpinner = new GridBagConstraints();
		gbc_popGrowthSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_popGrowthSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_popGrowthSpinner.gridx = 1;
		gbc_popGrowthSpinner.gridy = 3;
		colonyPanel.add(popGrowthSpinner, gbc_popGrowthSpinner);
		
		JLabel lblHuser = new JLabel("H\u00E4user");
		lblHuser.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHuser = new GridBagConstraints();
		gbc_lblHuser.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHuser.insets = new Insets(0, 0, 5, 5);
		gbc_lblHuser.gridx = 0;
		gbc_lblHuser.gridy = 5;
		colonyPanel.add(lblHuser, gbc_lblHuser);
		
		JSpinner housesSpinner = new JSpinner();
		housesSpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_housesSpinner = new GridBagConstraints();
		gbc_housesSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_housesSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_housesSpinner.gridx = 1;
		gbc_housesSpinner.gridy = 5;
		colonyPanel.add(housesSpinner, gbc_housesSpinner);
		
		JLabel lblHauskapazitt = new JLabel("Hauskapazit\u00E4t");
		lblHauskapazitt.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHauskapazitt = new GridBagConstraints();
		gbc_lblHauskapazitt.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHauskapazitt.insets = new Insets(0, 0, 5, 5);
		gbc_lblHauskapazitt.gridx = 0;
		gbc_lblHauskapazitt.gridy = 6;
		colonyPanel.add(lblHauskapazitt, gbc_lblHauskapazitt);
		
		JSpinner houseCapacitySpinner = new JSpinner();
		houseCapacitySpinner.setModel(new SpinnerNumberModel(new Integer(10), new Integer(1), null, new Integer(1)));
		GridBagConstraints gbc_houseCapacitySpinner = new GridBagConstraints();
		gbc_houseCapacitySpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_houseCapacitySpinner.insets = new Insets(0, 0, 5, 0);
		gbc_houseCapacitySpinner.gridx = 1;
		gbc_houseCapacitySpinner.gridy = 6;
		colonyPanel.add(houseCapacitySpinner, gbc_houseCapacitySpinner);
		
		JLabel lblHauspreis = new JLabel("Hauspreis");
		lblHauspreis.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblHauspreis = new GridBagConstraints();
		gbc_lblHauspreis.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblHauspreis.insets = new Insets(0, 0, 5, 5);
		gbc_lblHauspreis.gridx = 0;
		gbc_lblHauspreis.gridy = 8;
		colonyPanel.add(lblHauspreis, gbc_lblHauspreis);
		
		JSpinner housePriceSpinner = new JSpinner();
		housePriceSpinner.setModel(new SpinnerNumberModel(new Float(100), new Float(1), null, new Float(1)));
		GridBagConstraints gbc_housePriceSpinner = new GridBagConstraints();
		gbc_housePriceSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_housePriceSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_housePriceSpinner.gridx = 1;
		gbc_housePriceSpinner.gridy = 8;
		colonyPanel.add(housePriceSpinner, gbc_housePriceSpinner);
		
		JPanel buttonPanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) buttonPanel.getLayout();
		GridBagConstraints gbc_buttonPanel = new GridBagConstraints();
		gbc_buttonPanel.gridwidth = 2;
		gbc_buttonPanel.insets = new Insets(0, 0, 0, 5);
		gbc_buttonPanel.fill = GridBagConstraints.BOTH;
		gbc_buttonPanel.gridx = 0;
		gbc_buttonPanel.gridy = 1;
		frame.getContentPane().add(buttonPanel, gbc_buttonPanel);
		
		JButton startBtn = new JButton("Simulation starten");
		startBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// set colony variables
				Colony.POPULATION_GROWTH = (float) popGrowthSpinner.getValue();
				Colony.AREA_CONSTANT = (int) houseCapacitySpinner.getValue();
				Colony.GENERATOR_GROWTH = (float) generatorGrowthSpinner.getValue();
				Colony.ENERGY_PROD_CONSTANT = (float) epgSpinner.getValue();
				Colony.EFFICIENCY = (float) efficiencySpinner.getValue();
				Colony.EPP = (float) eppSpinner.getValue();
				Colony.MINING_MOD = (float) miningModSpinner.getValue();
				Colony.SMELTING_MOD = (float) smeltingModSpinner.getValue();
				Colony.MINING_PERCENTAGE = (float) energyMiningSlider.getValue() / 100f;
				Colony.MINING_PERCENTAGE_POP = (float) populationMiningSlider.getValue() / 100f;
				Colony.HOUSE_PRICE = (float) housePriceSpinner.getValue();
				
				System.out.println(Colony.MINING_PERCENTAGE + " " + Colony.MINING_PERCENTAGE_POP);
				
				Main.startSimulation((int) populationSpinner.getValue(), (int) housesSpinner.getValue(), (int) generatorSpinner.getValue());
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		buttonPanel.add(startBtn);
		
		JButton cancelBtn = new JButton("Abbrechen");
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		buttonPanel.add(cancelBtn);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
