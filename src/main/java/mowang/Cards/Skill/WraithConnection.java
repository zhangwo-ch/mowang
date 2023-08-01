package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Action.DelayAAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

public class WraithConnection extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(WraithConnection.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public WraithConnection() {
        super(ID, WraithConnection.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.magicNumber = this.baseMagicNumber = 6;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ScryAction(this.magicNumber));
        addToBot(new DelayAAction(new AbstractGameAction() {
            @Override
            public void update() {
                ModHelper.CanReturn(8, new GiveAllEnemyServitorAction(magicNumber));
                if (ModHelper.CanReturn(12))
                    addToBot(new GiveAllEnemyServitorAction(magicNumber));
                this.isDone = true;
            }
        }
        ));
    }

    public void triggerOnGlowCheck() {
        if (ModHelper.CanReturn(12)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        } else if (ModHelper.CanReturn(8)) {
            this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
        } else
            this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(2);
    }
}