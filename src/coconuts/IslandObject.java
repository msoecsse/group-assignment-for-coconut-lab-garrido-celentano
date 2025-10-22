package coconuts;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// an object in the game, either something coming from the island or falling on it
// Each island object has a location and can determine if it hits another island object
// This is a domain class; do not introduce JavaFX or other GUI components here
public abstract class IslandObject {
    protected final int width;
    final int minimumTouchingDistance = 35;
    protected final OhCoconutsGameManager containingGame;
    protected int x, y;
    ImageView imageView = null;

    public IslandObject(OhCoconutsGameManager game, int x, int y, int width, Image image) {
        containingGame = game;
        if (image != null) {
            imageView = new ImageView(image);
            imageView.setPreserveRatio(true);
            imageView.setFitWidth(width);
        }
        this.x = x;
        this.y = y;
        this.width = width;
        display();
        //System.out.println(this + " left " + left() + " right " + right());
    }

    protected ImageView getImageView() {
        return imageView;
    }

    public void display() {
        if (imageView != null) {
            imageView.setLayoutX(x);
            imageView.setLayoutY(y);
        }
    }

    public boolean isHittable() {
        return this instanceof Crab || this instanceof Coconut || this instanceof LaserBeam;
    }

    protected int hittable_height() {
        return 0;
    }

    public boolean isGroundObject() {
        return this instanceof Beach;
    }

    public boolean isFalling() {
        return this instanceof Coconut;
    }

    public boolean canHit(IslandObject other) {
        if((this instanceof Beach && other instanceof Coconut)
                || (this instanceof Crab && other instanceof Coconut)
                || (this instanceof LaserBeam && other instanceof Coconut)){
            return true;
        }return false;
    }

    public boolean isTouching(IslandObject other) {
        if (this instanceof Beach && other instanceof Coconut && other.y >= 520){
            return true;
        }
        int deltaX = Math.abs(other.x - this.x);
        int deltaY = Math.abs(other.y - this.y);

        return deltaY <= minimumTouchingDistance && deltaX <= minimumTouchingDistance;
    }

    public abstract void step();
}
