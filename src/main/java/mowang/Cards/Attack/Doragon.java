package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import com.megacrit.cardcrawl.rooms.AbstractRoom;
import mowang.Cards.AbstractHealCard;
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
		if(AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
			this.action = new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.magicNumber), this.magicNumber);
	}

	@Override
	public void use(AbstractPlayer p, AbstractMonster m) {
		addToBot((AbstractGameAction)new DamageAction((AbstractCreature)m, new DamageInfo((AbstractCreature)p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
		addToBot((AbstractGameAction)new ApplyPowerAction((AbstractCreature)p, (AbstractCreature)p, (AbstractPower)new StrengthPower((AbstractCreature)p, -1), -1));
	}
	
    @Override
	public void update() {
    	super.update();
		if(this.action != null && AbstractDungeon.player != null && AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT)
			this.action = new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new StrengthPower(AbstractDungeon.player, this.magicNumber), this.magicNumber);

	}
	
	@Override
	public void limitedUpgrade() {
		super.limitedUpgrade();
		this.upgradeDamage(6);
		this.upgradeMagicNumber(1);
	}
}
