package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.status.Burn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.GiveAllEnemyServitorAction;
import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;
import mowang.powers.ServitorPower;

import java.util.Iterator;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class FlamePact extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(FlamePact.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public FlamePact() {
        super(ID, FlamePact.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE,null);
        setupMagicNumber(3);
        tags.add(Recovery);
        action = new AbstractGameAction() {
            @Override
            public void update() {
                Iterator<AbstractCard> var = AbstractDungeon.player.hand.group.iterator();
                while (var.hasNext()){
                    AbstractCard c= var.next();
                    if (c.hasTag(Recovery) && c.uuid != uuid){
                        c.retain = true;
                    }
                }
                isDone = true;
            }
        };
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToPlayer(new ServitorPower(p,magicNumber));
        addToBot(new GiveAllEnemyServitorAction(magicNumber));
        int count = 0;
        Iterator var4 = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();

        while(var4.hasNext()) {
            AbstractMonster m2 = (AbstractMonster)var4.next();
            if (!m2.isDeadOrEscaped()) {
                ++count;
                this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
            }
        }
        addToBot(new MakeTempCardInDiscardAction(new Burn(), count));
    }
    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(1);
    }
}