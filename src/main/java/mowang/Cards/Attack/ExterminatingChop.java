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
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        damageToEnemy(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        addToBot(new GiveAllEnemyServitorAction(6));
        ModHelper.CanReturn(19, new AbstractGameAction() {
            @Override
            public void update() {
                if (this.shouldCancelAction()) {
                    this.isDone = true;
                } else {
                    this.tickDuration();
                    if (this.isDone) {
                        AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.BLUNT_HEAVY, false));
                        this.target.damage(new DamageInfo(p, damage, damageTypeForTurn));
                        if (this.target.lastDamageTaken > 0) {
                            this.addToBot(new ApplyPowerAction(m,p,new ServitorPower(m,magicNumber)));
                            if (this.target.hb != null) {
                                this.addToTop(new VFXAction(new WallopEffect(this.target.lastDamageTaken, this.target.hb.cX, this.target.hb.cY)));
                            }
                        }

                        if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                            AbstractDungeon.actionManager.clearPostCombatActions();
                        } else {
                            this.addToTop(new WaitAction(0.1F));
                        }
                    }

                }
            }
        });
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        this.upgradeDamage(3);
        upgradeMagicNumber(2);
    }
}
