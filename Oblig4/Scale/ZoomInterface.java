package Oblig4.Scale;

import javafx.scene.image.ImageView;

/**
 * Created by Jo Øivind Gjernes on 06.03.2016.
 *
 * Interface for å beskrive hva som trengs for å kunne benytte Zoom-klassen.
 */
public interface ZoomInterface
{
	void zoom(Coords zoomTo);
	ImageView getZoomObject();
}
