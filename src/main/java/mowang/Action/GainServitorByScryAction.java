package mowang.Action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mowang.powers.ServitorPower;

import java.util.Iterator;

public class GainServitorByScryAction extends AbstractGameAction {
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    private final boolean upgraded;
    private float startingDuration;

    public GainServitorByScryAction(int numCards, boolean upgraded) {
        this.upgraded = upgraded;
        this.amount = numCards;
        if (AbstractDungeon.player.hasRelic("GoldenEye")) {
            AbstractDungeon.player.getRelic("GoldenEye").flash();
            this.amount += 2;
        }
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
    }

    public void update() {
        int amount = 0;
        CardGroup tmpGroup = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        if (this.amount != -1) {
            for (int i = 0; i < Math.min(this.amount, AbstractDungeon.player.drawPile.size()); ++i) {
                tmpGroup.addToTop((AbstractCard) AbstractDungeon.player.drawPile.group.get(AbstractDungeon.player.drawPile.size() - i - 1));
            }
        }
        for (AbstractCard c : tmpGroup.group
        ) {
            if (c.type == AbstractCard.CardType.STATUS) {
                amount++;
            }
        }
        if (upgraded) {
            amount *= 2;
        }
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new ServitorPower(AbstractDungeon.player, amount)));

        this.isDone = true;
    }

    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("ReprogramAction");
        TEXT = uiStrings.TEXT;
    }
}
