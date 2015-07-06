package com.ckx.thread.bounce;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bounce {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new BounceFrame();
				frame.setTitle("BounceThread");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
class BallRunnable implements Runnable{
	private Ball ball;
	private Component component;
	public static final int STEPS = 1000;
	public static final int DELAY = 3;
	
	public BallRunnable(Ball ball,Component component){
		this.ball = ball;
		this.component = component;
	}
	@Override
	public void run() {
		for (int i = 0; i < STEPS; i++) {
			try {
				ball.move(component.getBounds());
				component.repaint();
				Thread.sleep(DELAY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
class BounceFrame extends JFrame{
	private BallComponent comp;
	
	public BounceFrame(){
		setTitle("Bounce");
		comp = new BallComponent();
		add(comp,BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		addButton(buttonPanel,"Start",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addBall();
			}
		});
		addButton(buttonPanel,"Close",new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		add(buttonPanel,BorderLayout.SOUTH);
		pack();
	}
	public void addButton(Container c ,String title, ActionListener listener){
		JButton button = new JButton(title);
		c.add(button);
		button.addActionListener(listener);
	}
	public void addBall(){
			Ball ball = new Ball();
			comp.add(ball);
			Runnable r = new BallRunnable(ball,comp);
			Thread t = new Thread(r);
			t.start();
	}
}