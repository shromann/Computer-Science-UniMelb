import bagel.*;
import bagel.util.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Buy panel.
 */
public class BuyPanel extends Panels {

	// -------------------------------------------- Attributes -------------------------------------------- 
	// Background 
    private static Image background = new Image("res/images/buypanel.png");

   	// Position Co-ordinates, Constant Symbol(s) and Fonts
    private double xOffset = 64;
    private double yOffset = (background.getHeight()/2) - 10;
    private double gap = 120;
    private double xPriceOffset = tank.getWidth() - 30;
    private double yPriceOffset = tank.getHeight() + 25;
    private double xPurchase = (background.getWidth()/2) - 40;
    private double yPurchase = 17;
    private double yPurchaseOffset = 10;
    private double xMoney = background.getWidth() - 200;
    private double yMoney = background.getHeight() - yOffset;
    private final String dollar = "$";
    private final Font prices = new Font("res/fonts/DejaVuSans-Bold.ttf", 20);
    private final Font keys = new Font("res/fonts/DejaVuSans-Bold.ttf", 14);
    private final Font funds = new Font("res/fonts/DejaVuSans-Bold.ttf", 45);

    // Purchase Items
    private static final String tankImg = "res/images/tank.png";
    private static final Image tank = new Image(tankImg);
    private final Integer tankPrice = 250;

    private static final String superTankImg = "res/images/supertank.png";
    /**
     * The constant superTank.
     */
    public static final Image superTank = new Image(superTankImg);
    private final Integer superTankPrice = 600;

    private static final String airsupportImg = "res/images/airsupport.png";
    /**
     * The constant airSupport.
     */
    public static final Image airSupport = new Image(airsupportImg);
    private final Integer airSupportPrice = 500;

    // Item purchasing
    private boolean itemSelected = false;
    private Image selectedItem;

	// -------------------------------------------- Methods ----------------------------------------------- 
    // Render out the buy panel
    public void render(){
        background.drawFromTopLeft(0, 0);
        renderKeyBinds();

        tank.draw(xOffset, yOffset);
        prices.drawString((dollar + tankPrice), xPriceOffset, yPriceOffset, color(tankPrice));

        superTank.draw((xOffset + gap), yOffset);
        prices.drawString((dollar + superTankPrice), xPriceOffset + gap, yPriceOffset, color(superTankPrice));

        airSupport.draw((xOffset + (2*gap)), yOffset);
        prices.drawString((dollar + airSupportPrice), xPriceOffset + (2*gap), yPriceOffset, color(airSupportPrice));

        funds.drawString(dollar + ShadowDefend.getFunds(), xMoney, yMoney);

    }

    @Override
    public void purchasing(Input input){
        if(input.wasPressed(MouseButtons.LEFT) && itemSelected){
            if(!ShadowDefend.map.hasProperty((int) input.getMouseX(),(int) input.getMouseY(),"blocked")){
                if(selectedItem.equals(tank)){
                    ShadowDefend.towers.add(new Tank(input.getMousePosition(), tankImg));
                    itemSelected = false;
                }
                if(selectedItem.equals(superTank)){
                    ShadowDefend.towers.add(new SuperTank(input.getMousePosition(), superTankImg));
                    itemSelected = false;
                }
                if(selectedItem.equals(airSupport)){
                    Point pos = new Point(0, input.getMouseY());
                    if (PassiveTower.horizontal == true) pos = new Point(0, input.getMouseY());
                    if (PassiveTower.horizontal == false) pos = new Point(input.getMouseX(), 0);
                    ShadowDefend.towers.add(new AirSupport(pos, airsupportImg, PassiveTower.horizontal));

                    if (PassiveTower.horizontal){
                        PassiveTower.horizontal = false;
                    }
                    else {
                        PassiveTower.horizontal = true;
                    }
                    itemSelected = false;
                }
            }
        }
        if (input.wasPressed(MouseButtons.LEFT) && !itemSelected) {
            if (tank.getBoundingBoxAt(new Point(xOffset, yOffset)).intersects(input.getMousePosition()) && color(tankPrice).equals(green)) {
                selectedItem = tank;
                itemSelected = true;
                ShadowDefend.setFunds(ShadowDefend.getFunds() - tankPrice);
            } else if (superTank.getBoundingBoxAt(new Point(xPriceOffset + gap, yOffset)).intersects(input.getMousePosition()) && color(superTankPrice).equals(green)) {
                selectedItem = superTank;
                itemSelected = true;
                ShadowDefend.setFunds(ShadowDefend.getFunds() - superTankPrice);
            } else if (airSupport.getBoundingBoxAt(new Point(xPriceOffset + (2 * gap), yOffset)).intersects(input.getMousePosition()) && color(airSupportPrice).equals(green)) {
                selectedItem = airSupport;
                itemSelected = true;
                ShadowDefend.setFunds(ShadowDefend.getFunds() - airSupportPrice);
            }
        }
        if(itemSelected){
            selectedItem.draw(input.getMouseX(),input.getMouseY());
        }
    }




    // set the valid colour
    @Override
    protected DrawOptions color(double price){
        if (ShadowDefend.getFunds() >= price){
            return green;
        }
        return red;
    }

    // render out the key binds
    private void renderKeyBinds(){
        keys.drawString("Key Binds:", xPurchase, yPurchase);
        keys.drawString("S - Start Wave", xPurchase, yPurchase + yPurchase + yPurchaseOffset);
        keys.drawString("L - Increase Timescale", xPurchase, yPurchase + 2*yPurchase + yPurchaseOffset);
        keys.drawString("K - Decrease Timescale", xPurchase, yPurchase + 3*yPurchase + yPurchaseOffset);
    }

}
