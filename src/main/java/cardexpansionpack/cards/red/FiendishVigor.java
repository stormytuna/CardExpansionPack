package cardexpansionpack.cards.red;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.RegenPower;

import cardexpansionpack.cards.BaseCard;
import cardexpansionpack.util.CardStats;

public class FiendishVigor extends BaseCard {
    public static final String ID = makeID("FiendishVigor");

    private static final CardStats INFO = new CardStats(
        CardColor.RED,
        CardType.SKILL,
        CardRarity.RARE,
        CardTarget.SELF,
        1
    );

    private static final int REGEN_AMOUNT = 5;
    private static final int HP_LOSS = 4;

    public FiendishVigor() {
        super(ID, INFO);
        setCostUpgrade(0);
        setMagic(REGEN_AMOUNT);
        setExhaust(true);
    }

    @Override
    public AbstractCard makeCopy() {
        return new FiendishVigor();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new RegenPower(p, magicNumber), magicNumber));
        addToBot(new DamageAction(p, new DamageInfo(p, HP_LOSS, DamageType.HP_LOSS), AttackEffect.FIRE));
    }
}

