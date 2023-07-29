package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;

public class EmptyAction extends AbstractGameAction {

    public EmptyAction() {}
    
    public void update() {
        this.isDone = true;
    }
}
