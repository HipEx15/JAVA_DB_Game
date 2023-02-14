package State;

import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import static Game.Assets.Resume;

//State-ul unde este prezentata povestea jocului dar si controalele pentru jucator.
public class StoryState extends State{

    private UIManager uiManager;

    public StoryState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUIManager(uiManager);
        isUIManagerActive = true;

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 120, 600, 155, 45, () -> {
            isUIManagerActive = false;
            if (hasGameStarted){
                handler.getGame().gamepauseState.setUIManagerActive(true);
                State.setState(handler.getGame().gamepauseState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
            //handler.getGame().audioManager.playMusic();
        }, "Meniu"));
    }

    @Override
    protected UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        if (isUIManagerActive){
            uiManager.tick();
        }
        if (handler.getKeyManager().esc){
            if (hasGameStarted){
                handler.getGame().gamepauseState.setUIManagerActive(true);
                State.setState(handler.getGame().gamepauseState);
            }
            else{
                handler.getGame().menuState.setUIManagerActive(true);
                State.setState(handler.getGame().menuState);
            }
        }
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Resume,0,0,null);

        String title1 = "Poveste";
        DrawText(title1, handler.getWidth()/2 - 155, 100, g, 30);

        String title2 = "Controale si Informatii";
        DrawText(title2, handler.getWidth()/2 - 400, 415, g, 30);

        g.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        g.setColor(Color.WHITE);

        String tmp1 = "       Jocul “Temnita Vrajitorilor” se desfasoara intr-o temnita, numita “Consiliul Arhidemonilor”, in care personajul principal, ";
        String tmp2 = "Altair, un vrajitor ce apartine taberei Arhanghelilor, cei care doresc binele tuturor, este infiltrat in tabara opusa, respectiv ";
        String tmp3 = "cea a Arhidemonilor. Identitatea acestuia a fost tinuta ascunsa mult timp, pana cand intr-o zi, din cauza modului sau de a fi ";
        String tmp4 = "si a indoielilor provocate privind deciziile luate, este compromisa. Altair, se afla acum intr-o dilema majora, intrucat este o-";
        String tmp5 = "bligat in a alege ce va face mai departe: sa evadeze din temnita sau sa incheie aceasta lupta dintre cele doua tabere ucigand  ";
        String tmp6 = "liderul Arhidemonilor.";
        String tmp7 = "       Dat fiind pregatirea sa pentru lupta cea mare dintre cele doua tabere, decide sa ramana si sa-si sacrifice viata pentru ";
        String tmp8= "Arhangheli. Astfel, personajul principal se aventureaza cu mult curaj in temnita Arhidemonilor, luptandu-se cu diferitii vraji- ";
        String tmp9 = "tori inamici, in cautarea marelui lider si anihilarea acestuia.";
        String tmp10 = "- W sau Sageata Sus: Pentru a sari";
        String tmp11 = "- A/D si Sageata Stanga/Dreapta: Pentru a merge";
        String tmp12 = "- Space: pentru a trage cu magie";
        String tmp13 = "- ESC: pentru a pune pauza jocului";
        String tmp14 = "Dificultate: ";
        String tmp15 = "- USOR -> 1 ";
        String tmp16 = "- MEDIU -> 2 ";
        String tmp17 = "- GREU -> 3 ";

        g.drawString(tmp1, 30, 150);
        g.drawString(tmp2, 30, 175);
        g.drawString(tmp3, 30, 200);
        g.drawString(tmp4, 30, 225);
        g.drawString(tmp5, 30, 250);
        g.drawString(tmp6, 30, 275);
        g.drawString(tmp7, 30, 300);
        g.drawString(tmp8, 30, 325);
        g.drawString(tmp9, 30, 350);
        g.drawString(tmp10, 30, 475);
        g.drawString(tmp11, 30, 500);
        g.drawString(tmp12, 30, 525);
        g.drawString(tmp13, 30, 550);
        g.drawString(tmp14, 1050, 475);
        g.drawString(tmp15, 1050, 500);
        g.drawString(tmp16, 1050, 525);
        g.drawString(tmp17, 1050, 550);


        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.setColor(Color.YELLOW);
        g.drawString(Integer.toString(handler.getGame().GetFps()), handler.getWidth()-30, 30);

        if (isUIManagerActive){
            uiManager.render(g);
        }
    }
}
