package com.pixel.input;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.GameContainer;

import com.pixel.communication.CommunicationClient;
import com.pixel.communication.packet.PacketLoadInterior;
import com.pixel.gui.GUIBox;
import com.pixel.gui.GUIKeyBindingButton;
import com.pixel.start.PixelRealms;
import com.pixel.world.World;

public class KeyboardListener {

	public static HashMap<String, KeyBinding> keyBindings = new HashMap<String, KeyBinding>();
	public static GUIBox selectedTextBox = null;
	public static ArrayList<GUIBox> textBoxes = new ArrayList<GUIBox>();
	public static GUIKeyBindingButton curKBB;
	public static boolean backSpace = false;

	public static void clearKeyBindings() {
		keyBindings = new HashMap<String, KeyBinding>();
	}
	
	public static void addKeyBinding(String s, int i) {
		keyBindings.put(s, new KeyBinding(i));
	}

	public static void update(GameContainer gc, int delta) {
		for (int i = 0; i < keyBindings.values().toArray().length; i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).tick();
		}
		
	}
	
	public static void checkSelection() {
		boolean flag = false;
		for (int i = 0; i < textBoxes.size(); i++) {
			if (textBoxes.get(i).selected) {
				flag = true;
			}
		}
		if (!flag) {
			selectedTextBox = null;
		}
	}
	
	public static void setSelectedTextBox(GUIBox tb) {
		selectedTextBox = tb;
	}
	
	public static void registerTextBox(GUIBox tb) {
		textBoxes.add(tb);
	}
	
	public static void setCurrentKeyBindingButton(GUIKeyBindingButton b) {
		if (curKBB != null) {
			curKBB.deselect();
		}
		curKBB = b;
	}

	public static void keyPressed(int code, char v) {
		checkSelection();

		if (World.loaded) {

//			if (code == KeyCode.KEY_N) {
//				PixelRealms.world.setTile((int)PixelRealms.world.player.getX(), (int)PixelRealms.world.player.getY(), 7, 0);
//			}
			
			if (code == KeyCode.KEY_ENTER) {

				if (!PixelRealms.world.player.interfaceManager.chat.box.getPressed()) {

					PixelRealms.world.player.interfaceManager.chat.box.selected = true;

				} else {

					PixelRealms.world.player.interfaceManager.chat.send();
					PixelRealms.world.player.interfaceManager.chat.box.selected = false;

				}

				return;

			}

		}

		if (selectedTextBox != null) {
			String t = selectedTextBox.getText();
			
			if (code == KeyCode.KEY_BACK) {
				backSpace = true;
				if (t.length() > 0) {
					selectedTextBox.setText(t.substring(0, t.length()-1));
				}
			}
			return;
		}

		if (World.loaded) {

			switch(code) {

			case KeyCode.KEY_1:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(0, 0);
				break;

			case KeyCode.KEY_2:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(1, 0);
				break;

			case KeyCode.KEY_3:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(2, 0);
				break;

			case KeyCode.KEY_4:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(3, 0);

			case KeyCode.KEY_5:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(4, 0);

				break;

			case KeyCode.KEY_6:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(5, 0);
				break;

			case KeyCode.KEY_7:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(6, 0);
				break;

			case KeyCode.KEY_8:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(7, 0);
				break;

			case KeyCode.KEY_9:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(8, 0);
				break;

			case KeyCode.KEY_0:
				PixelRealms.world.player.interfaceManager.hotbarWindow.selectSlot(9, 0);
				break;
				
			case KeyCode.KEY_F:
				PixelRealms.world.player.testLight();
				break;
				
			}

		}

		if (curKBB != null) {
			curKBB.deselectWithKeyCode(code);
			curKBB = null;
		}

		for (int i = 0; i < keyBindings.values().toArray().length; i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onPressed(code);
		}

	}

	public static void keyReleased(int code, char v) {
		
		checkSelection();
		if (selectedTextBox != null) {
			
			if (!backSpace && selectedTextBox.getText().length() < selectedTextBox.overflowLimit && (Character.isLetter(v) || Character.isSpaceChar(v) || Character.isDigit(v) || v == '/')) {
				String t = selectedTextBox.getText();
				selectedTextBox.setText(t + v);
				t = selectedTextBox.getText();
				
			}
			if (code == KeyCode.KEY_BACK) {
				backSpace = false;
			}
			
			return;
			
		}

		for (int i = 0; i < keyBindings.values().toArray().length; i++) {
			((KeyBinding) keyBindings.values().toArray()[i]).onReleased(code);
		}

		if (World.loaded) {
			
			if (code == KeyCode.KEY_E) {
				if (PixelRealms.world.player.door) {
				PixelRealms.world.player.door = false;
					if (PixelRealms.world.player.inside)
						PixelRealms.world.leaveInterior();
					else
						CommunicationClient.addPacket(new PacketLoadInterior(PixelRealms.world.player.currentlySelectedInterior));
					
				}
			}
			
		}
			
		
		if (code == KeyCode.KEY_BACK) {
			
			backSpace = false;
			
		}	
		
	}
	
}
