package mowang.Cards.Attack;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.WallopEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import mowang.Action.FetchAllBurnFromDisCardPileAction;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import static com.megacrit.cardcrawl.cards.AbstractCard.CardType.ATTACK;

public class ExterminatingChop extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(ExterminatingChop.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public ExterminatingChop() {
        super(ID, ExterminatingChop.class.getSimpleName(),
                cardStrings, 2, ATTACK, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        this.setupDamage(10);
        setupMagicNumber(6);
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToAllEnemies(AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new GiveAllEnemyServitorAction(magicNumber));
        ModHelper.CanReturn(8, new ApplyPowerAction(p,p,new ServitorPower(p,magicNumber)));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
        upgradeMagicNumber(2);
    }
}
