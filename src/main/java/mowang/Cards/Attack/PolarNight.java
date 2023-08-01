package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import mowang.Action.PlayCardAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import mowang.ModCore.ExampleMod;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class PolarNight extends AbstractExampleCard {
	public static final String ID = ModHelper.MakePath(PolarNight.class.getSimpleName());
	private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	private static int counter;

	public PolarNight() {
		super(ID, PolarNight.class.getSimpleName(),
				cardStrings, 3, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
		this.damage = this.baseDamage = 7;
		counter = 0;
	}

	public void checkDiscardPile() {
		int temp = AbstractDungeon.player.discardPile.size();
		if (temp < counter) {
			//AbstractDungeon.actionManager.addToBottom(new PlayCardAction(this.makeCopy()));
    		addToBot((AbstractGameAction)new GainEnergyAction(1));
		} 
		if (temp != counter)
			counter = temp; 
	}
	/*
	@Override
    public void update() {
        super.update();
		checkDiscardPile();
	}
	*/
	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		for(int i=0;i<3;i++)
			addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
	}
	

	@Override
	public void limitedUpgrade() {
		super.limitedUpgrade();
		this.upgradeDamage(2);
	}
}
