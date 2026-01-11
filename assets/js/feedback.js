/**
 * Feedback Page — Human verification and email reveal
 * Protects email from bots using simple math challenge
 */

// Global state
let correctAnswer;

// ROT13 decode (simple obfuscation)
function rot13(str) {
  return str.replace(/[a-zA-Z]/g, function(c) {
    return String.fromCharCode(
      (c <= 'Z' ? 90 : 122) >= (c = c.charCodeAt(0) + 13) ? c : c - 26
    );
  });
}

// Generate random math question
function generateQuestion() {
  const num1El = document.getElementById('num1');
  const num2El = document.getElementById('num2');
  const num3El = document.getElementById('num3');
  
  if (!num1El || !num2El || !num3El) return;
  
  const num1 = Math.floor(Math.random() * 10) + 1;
  const num2 = Math.floor(Math.random() * 10) + 1;
  const num3 = Math.floor(Math.random() * 10) + 1;
  
  num1El.textContent = num1;
  num2El.textContent = num2;
  num3El.textContent = num3;
  
  correctAnswer = num1 + num2 + num3;
}

// Verify human answer
function verifyHuman() {
  const answerInput = document.getElementById('math-answer');
  const errorMsg = document.getElementById('error-msg');
  const emailReveal = document.getElementById('email-reveal');
  const decodedEmail = document.getElementById('decoded-email');
  
  if (!answerInput || !errorMsg || !emailReveal || !decodedEmail) return;
  
  const userAnswer = parseInt(answerInput.value);
  
  // Email is stored as ROT13 encoded
  const encodedEmail = 'enawrrgpub@tznvy.pbz';
  
  if (userAnswer === correctAnswer) {
    // Correct! Reveal email
    const email = rot13(encodedEmail);
    decodedEmail.textContent = email;
    emailReveal.classList.remove('email-hidden');
    emailReveal.classList.add('email-visible');
    errorMsg.classList.remove('error-visible');
    errorMsg.classList.add('error-hidden');
    
    // Add click to copy
    decodedEmail.onclick = function() {
      navigator.clipboard.writeText(email);
      this.textContent = '✓ Copied!';
      setTimeout(() => {
        this.textContent = email;
      }, 2000);
    };
  } else {
    // Wrong answer
    errorMsg.textContent = '❌ Incorrect answer. Please try again.';
    errorMsg.classList.remove('error-hidden');
    errorMsg.classList.add('error-visible');
    generateQuestion(); // New question
  }
}

// Initialize on page load
document.addEventListener('DOMContentLoaded', generateQuestion);

// Expose verifyHuman globally for onclick handler
window.verifyHuman = verifyHuman;
