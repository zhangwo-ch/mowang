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
import mowang.Cards.AbstractExampleCard;
import mowang.Helpers.ModHelper;

import static mowang.Characters.MyCharacter.Enums.Recovery;

public class BloodyPact extends AbstractExampleCard {
    public static final String ID = ModHelper.MakePath(BloodyPact.class.getSimpleName());
    private static CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    public BloodyPact() {
        super(ID, BloodyPact.class.getSimpleName(),
                cardStrings, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.NONE);
        setupMagicNumber(3);
        tags.add(Recovery);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new LoseHPAction(p,p,magicNumber));
        this.addToBot(new ActionAction());
    }

    @Override
    public void update() {
        super.update();
        ModHelper.GetMostLeftCard();
    }

    @Override
    public void limitedUpgrade() {
        super.limitedUpgrade();
        upgradeMagicNumber(-1);
    }
}