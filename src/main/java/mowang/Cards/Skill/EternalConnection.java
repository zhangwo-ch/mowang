package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.ScryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Action.GainServitorByScryAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.Action.AfterScryCanReturnAction;

public class EternalConnection extends AbstractExampleCard {
	public static final String ID = ModHelper.MakePath(EternalConnection.class.getSimpleName());
	private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	public EternalConnection() {
		super(ID, EternalConnection.class.getSimpleName(), cardStrings, 1, AbstractCard.CardType.SKILL, AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.NONE);

		setupMagicNumber(6);
	}


	public void use(AbstractPlayer p, AbstractMonster m) {
		int[] x = {0};
		addToBot(new GainServitorByScryAction(this.magicNumber, this.upgraded, x));
		
		addToBot(new ScryAction(this.magicNumber));
		
		addToBot(new AfterScryCanReturnAction(this.magicNumber, x));
	}

	public void limitedUpgrade() {
		super.limitedUpgrade();
		upgradeMagicNumber(2);
		upgradeDescription(cardStrings);
	}
}
