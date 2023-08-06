package mowang.Cards.Skill;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import mowang.Action.ActionAction;
import mowang.Action.MarchAction;
import mowang.Action.ReplaceBurnAction;
import mowang.Action.ResetRecoveryAction;
import mowang.Cards.AbstractExampleCard;
import mowang.Cards.AbstractHealCard;
import mowang.Helpers.ModHelper;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class BloodyPact extends AbstractHealCard {
    public static final String ID = ModHelper.MakePath(BloodyPact.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BloodyPact() {
        super(ID, BloodyPact.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY,null);
        setupMagicNumber(3);
        tags.add(Recovery);
        action = new ActionAction();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p,p,magicNumber));
        this.addToBot(new ActionAction());
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(-1);
    }
}