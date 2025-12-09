/*
Title: Mystery of Silver Mountain Map Button
Author: Chris Oxlade & Judy Tatchell
Translator: David Sarkies
Version: 1.1
Date: 9 December 2025
Source: Added Title 
*/

package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import view.GamePanel;


/**
 * An {@link ActionListener} that switches the {@link GamePanel}
 * to its map view when the associated UI control (such as a button)
 * is activated.
 * <p>
 * Typical usage:
 * <pre>{@code
 * JButton mapButton = new JButton("Map");
 * mapButton.addActionListener(new MapButton(gamePanel));
 * }</pre>
 */
public class MapButton implements ActionListener {

    /** The game panel whose map view will be displayed. */
	private final GamePanel panel;
	
    /**
     * Constructs a {@code MapButton} that will show the map view
     * on the specified {@link GamePanel}.
     *
     * @param panel the game panel that provides the map view;
     *              must not be {@code null}
     * @throws NullPointerException if {@code panel} is {@code null}
     */
	public MapButton(GamePanel panel) {
			this.panel = Objects.requireNonNull(panel,"GamePanel cannot be null");
	}
	
    /**
     * Invoked when an action occurs on the associated UI component.
     * This implementation switches the {@link GamePanel} to its map view.
     *
     * @param event the action event triggered by the user interaction
     */
	@Override
	public void actionPerformed(ActionEvent event) {
		panel.showMapView();
	}
}

/* 3 December 2025 - Created File
 * 8 December 2025 - Increased version number
 * 9 December 2025 - Added Title
 */
