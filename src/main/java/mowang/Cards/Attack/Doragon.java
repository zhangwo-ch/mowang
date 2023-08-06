package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import mowang.Cards.AbstractHealCard;
import mowang.Cards.Skill.Lantern;
import mowang.Helpers.ModHelper;
import com.megacrit.cardcrawl.cards.DamageInfo;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class Doragon extends AbstractHealCard {
	public static final String ID = ModHelper.MakePath(Doragon.class.getSimpleName());
	private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

	public Doragon() {
		super(ID, Doragon.class.getSimpleName(),
				cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY, null);
		this.damage = this.baseDamage = 23;
		this.magicNumber = this.baseMagicNumber = 3;
        this.action = new AbstractGameAction() {
        	@Override
        	public void update() {
        		addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, magicNumber), magicNumber));
        		this.isDone = true;
        	}
        };
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, -1), -1));
	}
	
	
	@Override
	public void limitedUpgrade() {
		super.limitedUpgrade();
		this.upgradeDamage(6);
		this.upgradeMagicNumber(1);
	}
}
