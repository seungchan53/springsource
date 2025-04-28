// checkbox 클릭이 되면
// checkbox value, data-id 가져오기

document.querySelector(".list-group").addEventListener("click", (e) => {
  // 어떤 labal 안 chenkbox 에서 이벤트가 발생했는지 확인
  const chk = e.target;
  console.log(chk);
  // checkbox 체크, 해제 여부확인
  console.log(chk.checked);

  // id 가져오기
  // closet("선택자") : 부모에서 제일 가까운 요소 찾기
  // data- 속성 가져오기 : dataset
  const id = chk.closest("label").dataset.id;
  console.log(id);

  // actionForm 찾은 후 값 변경하기
  const actionForm = document.querySelector("#actionForm");
  actionForm.id.value = id;
  actionForm.completed.value = chk.checked;

  actionForm.submit();
});
