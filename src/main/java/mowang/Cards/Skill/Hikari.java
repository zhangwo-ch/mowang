package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Action.HikariAction;
import mowang.Helpers.ModHelper;
import mowang.Cards.Skill.Lantern;

public class Hikari extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(Hikari.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public Hikari() {
        super(ID, Hikari.class.getSimpleName(),
                cardStrings, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        this.selfRetain = true;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	if(p.hasPower("Dexterity"))
    		addToBot(new HikariAction(Lantern.ID, p.getPower("Dexterity").amount));
    	else
    		addToBot(new HikariAction(Lantern.ID, 0));
    }
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
		this.rawDescription = cardStrings.UPGRADE_DESCRIPTION;
		initializeDescription();
		this.exhaust = false;
    }
}