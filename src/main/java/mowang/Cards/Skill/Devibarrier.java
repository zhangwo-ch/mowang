package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import mowang.Action.DemonLookAction;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Action.WraithStrikeHealAction;
import mowang.Action.DelayAAction;
import mowang.Action.DevibarrierAction;
import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;

import java.util.Iterator;

public class Devibarrier extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(Devibarrier.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Devibarrier() {
        super(ID, Devibarrier.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY, null);
        this.action = new AbstractGameAction() {
        	@Override
        	public void update() {
        		addToBot(new WraithStrikeHealAction(magicNumber));
        		this.isDone = true;
        	}
        };
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if(this.upgraded) addToBot(new GiveAllEnemyServitorAction(6));
        addToBot(new DelayAAction(new DevibarrierAction()));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		initializeDescription();
    }
}