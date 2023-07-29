package mowang.Cards.Attack;

import mowang.Action.AbsorbingChopAction;
import mowang.Action.EmptyAction;
import mowang.Cards.Skill.Lantern;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class RedAndSilver extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(RedAndSilver.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public RedAndSilver() {
        super(ID, RedAndSilver.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        this.damage = this.baseDamage = 14;
        this.magicNumber = this.baseMagicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    	addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
    	if (ModHelper.CanReturn(4, new EmptyAction())){
    		for(int i=0;i<this.magicNumber;i++) {
    			Lantern c = new Lantern();
    			addToBot((AbstractGameAction)new MakeTempCardInDiscardAction(c, true));
    		}
    	}
    	if (ModHelper.CanReturn(18)){
    		Lantern c = new Lantern();
    		addToBot((AbstractGameAction)new MakeTempCardInHandAction(c, 1));
    	}
    }
    
    public void triggerOnGlowCheck() {
		if (ModHelper.CanReturn(18)) {
			this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
		} else if(ModHelper.CanReturn(4)){
			this.glowColor = AbstractCard.GREEN_BORDER_GLOW_COLOR.cpy();
		} else
			this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
	}
    
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(4);
        this.upgradeMagicNumber(1);
		upgradeDescription(cardStrings);
    }
}
